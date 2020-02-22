package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignsResponse extends BaseResponse{

    private List<Campaign> campaigns;
}
