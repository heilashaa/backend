package com.haapp.formicary.api.message.auth;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.domain.model.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse extends BaseResponse {

    private Token registrationResponse;
}

