package com.haapp.formicary.api.message.auth;

import com.haapp.formicary.api.model.UserDtoQQQQ;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AuthRequest {

    @Valid
    @NotNull
    private UserDtoQQQQ userDtoQQQQ;
}
