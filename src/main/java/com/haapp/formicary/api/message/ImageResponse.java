package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.ImageApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse extends BaseResponse {

    private ImageApi image;
}
