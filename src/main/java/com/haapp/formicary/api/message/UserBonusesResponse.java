package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.UserBonusApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBonusesResponse extends BaseResponse {

    private List<UserBonusApi> userBonuses;

}
