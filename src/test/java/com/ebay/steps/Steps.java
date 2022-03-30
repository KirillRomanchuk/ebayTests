package com.ebay.steps;

import com.ebay.websites.BaseWebSite;
import com.ebay.websites.WebSiteManager;

public abstract class Steps {
    protected final BaseWebSite site = WebSiteManager.getSiteInstance();
}
