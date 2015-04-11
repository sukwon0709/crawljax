package com.crawljax.examples;

import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.plugins.crawlgraphviz.CrawlGraphviz;

/**
 * Created by soh on 10/04/15.
 */
public class GraphvizPluginExample {

    /**
     * Run this method to start the crawl.
     */
    public static void main(String[] args) {
        CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor("http://demo.crawljax.com/");
        builder.crawlRules().clickDefaultElements();
        builder.crawlRules().click("div");
        builder.addPlugin(new CrawlGraphviz());
        CrawljaxRunner crawljax = new CrawljaxRunner(builder.build());
        crawljax.call();
    }
}
