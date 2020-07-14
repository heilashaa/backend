package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.UserBonusApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBonusResponse extends BaseResponse {

    private UserBonusApi userBonus;

}
