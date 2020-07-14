package com.haapp.formicary.api;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Error {

    private String code;
    private List<String> messages;
}
