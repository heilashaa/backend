package com.haapp.formicary.infrastructure;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.haapp.formicary.infrastructure.Constant.Headers.X_LOCALE;
import static com.haapp.formicary.infrastructure.Constant.Locales.DEFAULT_LOCALE;

@AllArgsConstructor
public class CommonDataInterceptor extends HandlerInterceptorAdapter {

    private CommonData commonData;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        commonData.setLocale(extractLocale(request));
        return true;
    }

    private Locale extractLocale(HttpServletRequest request) {
        String lang = request.getHeader(X_LOCALE);
        if (StringUtils.isNotBlank(lang)) {
            return Locale.forLanguageTag(lang);
        }
        return Locale.forLanguageTag(DEFAULT_LOCALE);
    }
}
