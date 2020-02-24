package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Article;
import com.haapp.formicary.domain.model.Campaign;
import com.haapp.formicary.domain.model.Image;
import com.haapp.formicary.persistence.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository repository;
    private final ModelMapper modelMapper;
    private final CampaignService campaignService;
    private final ArticleService articleService;
    private final FileUploadService fileUploadService;


    public Image addImageToCampaign(Long campaignId, MultipartFile image) {
        Campaign campaign = campaignService.findByIdRequired(campaignId);
        String url = fileUploadService.uploadImage("campaigns/" + campaign.getId(), image);
        com.haapp.formicary.persistence.model.Image dataImage = new com.haapp.formicary.persistence.model.Image();
        dataImage.setCampaign(modelMapper.map(campaign, com.haapp.formicary.persistence.model.Campaign.class));
        dataImage.setImageLink(url);
        dataImage = repository.save(modelMapper.map(dataImage, com.haapp.formicary.persistence.model.Image.class));
        return modelMapper.map(dataImage, Image.class);
    }

    public void deleteCampaignImage(Long imageId) {
        repository.deleteById(imageId);
    }

    public List<Image> findImagesByCampaignId(Long campaignId) {
        var images = repository.findByCampaignId(campaignId);
        return asList(modelMapper.map(images, Image[].class));
    }

    public Article updateArticleImage(Long articleId, MultipartFile image) {
        Article article = articleService.findByIdRequired(articleId);
        String url = fileUploadService.uploadImage("articles/" + article.getId(), image);
        article.setImageLink(url);
        return  articleService.save(article);
    }
}
