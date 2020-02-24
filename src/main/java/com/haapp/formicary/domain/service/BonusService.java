package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Bonus;
import com.haapp.formicary.domain.model.UserBonus;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.persistence.repository.BonusRepository;
import com.haapp.formicary.persistence.repository.UserBonusRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class BonusService {

    private final BonusRepository repository;
    private final UserBonusRepository userBonusRepository;
    private final ModelMapper modelMapper;
    private final CampaignService campaignService;
    private final UserService userService;

    public Bonus create(Long campaignId, Bonus bonus) {
        if (nonNull(bonus)) {
            var campaign = campaignService.findByIdRequired(campaignId);
            bonus.setCampaign(campaign);
            return save(bonus);
        }
        return null;
    }

    public Bonus update(Long id, Bonus bonus) {
        if (nonNull(bonus)) {
            var saved = findByIdRequired(id);
            modelMapper.map(bonus, saved);
            return save(saved);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Bonus findByIdRequired(Long id) {
        var optional = repository.findById(id);
        return optional.map(bonus -> modelMapper.map(bonus, Bonus.class))
                .orElseThrow(() -> new NotFoundException(""));
    }


    public List<Bonus> findByCampaignId(Long campaignId) {
        var result = repository.findAllByCampaignId(campaignId);
        return asList(modelMapper.map(result, Bonus[].class));
    }

    public List<UserBonus> findByUserId(Long userId) {
        var result = userBonusRepository.findAllByUserId(userId);
        return asList(modelMapper.map(result, UserBonus[].class));
    }

    public UserBonus addBonusToUser(Long userId, Long bonusId) {
        UserBonus userBonus = new UserBonus();
        userBonus.setUser(userService.findByIdRequired(userId));
        userBonus.setBonus(findByIdRequired(bonusId));
        var dataUserBonus = modelMapper.map(userBonus, com.haapp.formicary.persistence.model.UserBonus.class);
        dataUserBonus = userBonusRepository.save(dataUserBonus);
        return modelMapper.map(dataUserBonus, UserBonus.class);
    }


    public Bonus save(Bonus bonus) {
        var dataBonus = modelMapper.map(bonus, com.haapp.formicary.persistence.model.Bonus.class);
        dataBonus = repository.save(dataBonus);
        return modelMapper.map(dataBonus, Bonus.class);
    }
}
