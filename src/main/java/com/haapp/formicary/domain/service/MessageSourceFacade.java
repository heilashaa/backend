package com.haapp.formicary.domain.service;

import com.haapp.formicary.infrastructure.CommonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@AllArgsConstructor
public class MessageSourceFacade {

    private final MessageSource messageSource;

    private final CommonData commonData;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, Collections.emptyList().toArray(), commonData.getLocale());
        } catch (NoSuchMessageException e) {
            log.error(e.getMessage());
            return code;
        }
    }
}
