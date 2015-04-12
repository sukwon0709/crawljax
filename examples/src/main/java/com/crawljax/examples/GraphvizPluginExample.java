package com.crawljax.examples;

import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.plugins.crawlgraphviz.CrawlGraphviz;

/**
 * Created by soh on 10/04/15.
 */
public class GraphvizPluginExample {

    /**
     * Run this method to start the crawl.
     */
    public static void main(String[] args) {
        CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor("http://clementine/wordpress/");
        builder.crawlRules().clickDefaultElements();
        builder.crawlRules().click("div");
        builder.addPlugin(new CrawlGraphviz());

        InputSpecification loginForm = new InputSpecification();
        loginForm.field("user_login").setValue("alice");
        loginForm.field("user_pass").setValue("12345");
        builder.crawlRules().setInputSpec(loginForm);

        CrawljaxRunner crawljax = new CrawljaxRunner(builder.build());
        crawljax.call();
    }
}
