package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.TagApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagsResponse extends BaseResponse {

    private List<TagApi> tags;
}
