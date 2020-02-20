package com.haapp.formicary.api.message.auth;

import com.haapp.formicary.domain.model.LoginRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Valid
    @NotNull
    private LoginRequestDto loginRequestDto;
}
