package com.crawljax.examples;

import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.plugins.crawlgraphviz.CrawlGraphviz;

import java.util.concurrent.TimeUnit;

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

        // do not click on divs representing menus on left-side.
        builder.crawlRules().dontClick("div").withAttribute("class", "wp-menu-arrow");
        builder.crawlRules().dontClick("div").withAttribute("class", "wp-menu-image");
        builder.crawlRules().dontClick("div").withAttribute("class", "wp-menu-name");
        builder.crawlRules().dontClick("div").withAttribute("class", "collapse-button");

        // do not click on a menu on top right corner.
        builder.crawlRules().dontClick("div").withAttribute("class", "ab-sub-wrapper");

        // do not click on links irrelevant for the test.
        builder.crawlRules().dontClick("a").withAttribute("href", "profile.php");
        builder.crawlRules().dontClick("a").withAttribute("href", "tools.php");

        // explicitly set depth and runtime because default depth seems to be too small.
        builder.setMaximumDepth(15);
        builder.setMaximumRunTime(5, TimeUnit.HOURS);

        CrawljaxRunner crawljax = new CrawljaxRunner(builder.build());
        crawljax.call();
    }
}
