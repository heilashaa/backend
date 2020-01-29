package com.haapp.formicary.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    @UtilityClass
    public static final class Locales {

        public static final String EN_LOCALE = "en";
        public static final String RU_LOCALE = "ru";
        public static final String DEFAULT_LOCALE = "ru";
    }

    @UtilityClass
    public static final class Headers {

        public static final String X_LOCALE = "X-Locale";

    }

    @UtilityClass
    public static final class Service {

        public static final String BASIC_AUTH = "basicAuth";

    }

}
