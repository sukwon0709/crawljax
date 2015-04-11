package com.crawljax.examples;

import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.plugins.crawlgraphviz.CrawlGraphviz;

/**
 * Created by soh on 10/04/15.
 */
public class GraphvizPluginExample {

    /**
     * Run this method to start the crawl.
     */
    public static void main(String[] args) {
        CrawljaxRunner crawljax =
                new CrawljaxRunner(CrawljaxConfiguration.builderFor("http://demo.crawljax.com/")
                        .addPlugin(new CrawlGraphviz())
                        .build());
        crawljax.call();
    }
}
