package com.haapp.formicary.mapping;

import com.haapp.formicary.domain.model.BonusDto;
import com.haapp.formicary.persistence.model.Bonus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, UserMapper.class})
public interface BonusMapper {
    
    BonusMapper INSTANCE = Mappers.getMapper(BonusMapper.class);

    Bonus bonusDtoToBonus(BonusDto bonusDto);

    BonusDto bonusToBonusDto(Bonus bonus);
}
