package com.ebay.websites;

import com.ebay.utils.browsers.BrowserManager;

public class WebSiteManager {
    private static BaseWebSite site;

    public static BaseWebSite getSite(String siteUrl) {

        if (site != null)
            return site;

        site = getDesktopSite();

        site.loadPage(siteUrl);
        return site;
    }

    public static BaseWebSite getSiteInstance() {
        return site;
    }

    public static DesktopWebSite getDesktopSite() {
        return new DesktopWebSite();
    }

    public static void resetSite() {
        site = null;
        BrowserManager.quitSession();
    }


}
