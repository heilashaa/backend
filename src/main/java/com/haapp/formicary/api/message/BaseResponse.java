package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BaseResponse {

    private Error error = null;
}
