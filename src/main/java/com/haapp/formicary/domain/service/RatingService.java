package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.CampaignDto;
import com.haapp.formicary.domain.model.RatingDto;
import com.haapp.formicary.domain.model.UserDto;
import com.haapp.formicary.persistence.model.Rating;
import com.haapp.formicary.persistence.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {

    private RatingRepository ratingRepository;
    private ModelMapper modelMapper;
    private CampaignService campaignService;
    private UserService userService;

    public RatingDto updateRating(Long campaignId, RatingDto ratingDto){
        var company = campaignService.findByIdRequired(campaignId);
        var user = userService.getCurrentUser();
        var optional = findByUserAndCampaign(user, company);
        if(optional.isPresent()){
            RatingDto saved = optional.get();
            modelMapper.map(ratingDto, saved);
            return save(saved);
        } else {
            ratingDto.setUser(user);
            ratingDto.setCampaign(company);
            return save(ratingDto);
        }
    }

    public RatingDto save(RatingDto rating) {
        var dataRating = modelMapper.map(rating, Rating.class);
        dataRating = ratingRepository.save(dataRating);
        return modelMapper.map(dataRating, RatingDto.class);
    }


    public Optional<RatingDto> findByUserAndCampaign(UserDto user, CampaignDto campaign) {
        return ratingRepository.findByUserIdAndCampaignId(user.getId(), campaign.getId())
                .map(rating -> modelMapper.map(rating, RatingDto.class));
    }
}
