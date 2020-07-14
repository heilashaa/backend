package com.haapp.formicary.api.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> extends BaseResponse{

    private int size;
    private long totalElements;
    private int totalPages;
    private List<T> content;
    private int number;

}
