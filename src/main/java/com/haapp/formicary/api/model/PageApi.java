package com.haapp.formicary.api.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageApi<C> {

    private int size;

    private long totalElements;

    private int totalPages;

    private List<C> content;

    private int number;
}
