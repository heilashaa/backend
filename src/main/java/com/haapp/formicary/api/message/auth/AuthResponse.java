package com.haapp.formicary.api.message.auth;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.domain.model.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse extends BaseResponse {

    private AuthUser authUser;
}
