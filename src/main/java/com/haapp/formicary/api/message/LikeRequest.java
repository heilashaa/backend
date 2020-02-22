package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequest {

    private Like like;
}
