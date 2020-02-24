package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Campaign;
import com.haapp.formicary.domain.model.Rating;
import com.haapp.formicary.domain.model.User;
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

    public Rating updateRating(Long campaignId, Rating ratingDto){
        var company = campaignService.findByIdRequired(campaignId);
        var user = userService.getCurrentUser();
        var optional = findByUserAndCampaign(user, company);
        if(optional.isPresent()){
            Rating saved = optional.get();
            modelMapper.map(ratingDto, saved);
            return save(saved);
        } else {
            ratingDto.setUser(user);
            ratingDto.setCampaign(company);
            return save(ratingDto);
        }
    }

    public Rating save(Rating rating) {
        var dataRating = modelMapper.map(rating, com.haapp.formicary.persistence.model.Rating.class);
        dataRating = ratingRepository.save(dataRating);
        return modelMapper.map(dataRating, Rating.class);
    }


    public Optional<Rating> findByUserAndCampaign(User user, Campaign campaign) {
        return ratingRepository.findByUserIdAndCampaignId(user.getId(), campaign.getId())
                .map(rating -> modelMapper.map(rating, Rating.class));
    }
}
