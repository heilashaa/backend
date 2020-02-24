package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.TagApi;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagResponse extends BaseResponse {

    private TagApi tag;
}
