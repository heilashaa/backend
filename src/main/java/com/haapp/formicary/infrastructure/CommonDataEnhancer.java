package com.haapp.formicary.infrastructure;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.haapp.formicary.config.Constant.Headers.X_LOCALE;
import static com.haapp.formicary.config.Constant.Locales.DEFAULT_LOCALE;

@Slf4j
@AllArgsConstructor
public class CommonDataEnhancer extends HandlerInterceptorAdapter {

    private CommonData commonData;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        commonData.setLocale(extractLocale(request));
        return true;
    }

    private Locale extractLocale(HttpServletRequest request) {
        String lang = request.getHeader(X_LOCALE);
        if (StringUtils.isNotBlank(lang)) {
            try {
                return Locale.forLanguageTag(lang);
            } catch (Exception e) {
                log.error("Error extract locale from {}", lang);
            }
        }
        return Locale.forLanguageTag(DEFAULT_LOCALE);
    }
}
