package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.ImageApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagesResponse {

    private List<ImageApi> images;
}
