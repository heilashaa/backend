package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.RatingApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse extends BaseResponse {

    private RatingApi rating;
}
