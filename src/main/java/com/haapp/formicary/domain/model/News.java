package com.haapp.formicary.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private Long id;
    private String text;
    private String imageLink;
    private LocalDateTime creationDate;
    private Campaign campaign;
}
