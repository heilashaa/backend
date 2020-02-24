package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Campaign;
import com.haapp.formicary.domain.model.SearchCampaignsParams;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.persistence.specification.SearchCampaignsSpecification;
import com.haapp.formicary.persistence.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CampaignService {

    private final ModelMapper modelMapper;
    private final CampaignRepository repository;
    private final UserService userService;
    private final EntityManager entityManager;
    private final TagService tagService;

    @Autowired
    public CampaignService(final EntityManagerFactory entityManagerFactory,
                           final ModelMapper modelMapper,
                           final CampaignRepository repository,
                           final UserService userService,
                           final TagService tagService) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.userService = userService;
        this.tagService = tagService;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @PostConstruct
    public void initializeHibernateSearch() throws Exception {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }

    public Campaign create(Long userId, Campaign campaign) {
        if (nonNull(campaign)) {
            campaign.setUser(userService.findByIdRequired(userId));
            var tags = campaign.getTags();
            tags = tags.stream()
                    .map(tag-> tagService.findByNameOrCreate(tag.getName())).collect(Collectors.toSet());
            campaign.setTags(tags);
            return save(campaign);
        }
        return null;
    }

//    public Campaign create(Campaign campaign) {
//        if (nonNull(campaign)) {
//            campaign.setUser(userService.getCurrentUser());
//            var tags = campaign.getTags();
//            tags = tags.stream()
//                    .map(tag-> tagService.findByNameOrCreate(tag.getName())).collect(Collectors.toSet());
//            campaign.setTags(tags);
//            return save(campaign);
//        }
//        return null;
//    }

    public Campaign update(Long id, Campaign campaign) {
        var saved = findByIdRequired(id);
        modelMapper.map(campaign, saved);
        var tags = campaign.getTags();
        tags = tags.stream()
                .map(tag-> tagService.findByNameOrCreate(tag.getName())).collect(Collectors.toSet());
        saved.setTags(tags);
        return save(saved);
    }

    public List<Campaign> findByUser(Long userId) {
        var campaigns = repository.findDistinctByUserId(userId);
        return asList(modelMapper.map(campaigns, Campaign[].class));
    }


    public Campaign findByIdRequired(Long id) {
        var optional = repository.findById(id);
        return optional.map(campaign -> modelMapper.map(campaign, Campaign.class))
                .orElseThrow(() -> new NotFoundException(""));
    }

    public Campaign save(Campaign campaign) {
        var dataCampaign = modelMapper.map(campaign, com.haapp.formicary.persistence.model.Campaign.class);
        dataCampaign = repository.save(dataCampaign);
        return modelMapper.map(dataCampaign, Campaign.class);
    }

    public List<Campaign> search(String searchValue) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(com.haapp.formicary.persistence.model.Campaign.class)
                .get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard()
                .onFields("name", "description", "comments.text", "articles.text", "articles.headline")
                .matching(searchValue)
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery,
                com.haapp.formicary.persistence.model.Campaign.class);

        var result = jpaQuery.getResultList();

        return asList(modelMapper.map(result, Campaign[].class));
    }

    public com.haapp.formicary.domain.model.Page<Campaign> findAll(SearchCampaignsParams params, List<String> sortingParams, Integer page, Integer size) {
        List<Sort.Order> sortOrder;
        if (isNull(sortingParams) || sortingParams.isEmpty()) {
            sortOrder = Collections.singletonList(new Sort.Order(Sort.Direction.DESC, "id"));
        } else {
            sortOrder = sortingParams.stream()
                    .map(field -> new Sort.Order(Sort.Direction.DESC, field))
                    .collect(toList());
        }
        Page<com.haapp.formicary.persistence.model.Campaign> result;
        if (nonNull(params) && params.hasParams()) {
            result = repository.findAll(new SearchCampaignsSpecification(params), PageRequest.of(page, size, Sort.by(sortOrder)));
        } else {
            result = repository.findAll(PageRequest.of(page, size, Sort.by(sortOrder)));
        }
        Type pageType = new TypeToken<com.haapp.formicary.domain.model.Page<Campaign>>() {}.getType();
        return modelMapper.map(result, pageType);
    }
}
