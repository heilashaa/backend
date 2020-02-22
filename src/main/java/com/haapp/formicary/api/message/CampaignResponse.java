package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponse extends BaseResponse{

    private Campaign campaign;
}
