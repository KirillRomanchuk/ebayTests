package com.ebay.utils.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:general.properties",
})
public interface GeneralConfig extends Config {

    @Key("browser")
    String browserName();

    @Key("homePageUrl")
    String homePageUrl();
}

