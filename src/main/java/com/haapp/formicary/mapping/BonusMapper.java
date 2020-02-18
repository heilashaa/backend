package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.BonusDto;
import com.haapp.formicary.domain.model.Bonus;
import com.haapp.formicary.persistence.model.BonusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, UserMapper.class})
public interface BonusMapper {
    
    BonusMapper INSTANCE = Mappers.getMapper(BonusMapper.class);

    BonusDto bonusDomainToBonusDto(Bonus bonusDomain);

    Bonus bonusDtoToBonusDomain(BonusDto bonusDto);

    BonusEntity bonusDomainToBonusPersistence(Bonus bonusDomain);

    Bonus bonusPersistenceToBonusDomain(BonusEntity bonusPersistence);
}
