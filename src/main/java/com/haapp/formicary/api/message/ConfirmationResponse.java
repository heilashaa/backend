package com.haapp.formicary.api.message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationResponse extends BaseResponse {

    public static final ConfirmationResponse SUCCESS = new ConfirmationResponse(true);

    public Boolean success;
}

