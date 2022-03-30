package com.ebay.utils.configs;

import org.aeonbits.owner.ConfigFactory;

public class Configs {

    private static final GeneralConfig generalConfig = ConfigFactory.create(GeneralConfig.class);

    public static String browserName() {
        return generalConfig.browserName();
    }

    public static String homePageUrl() {
        return generalConfig.homePageUrl();
    }

}
