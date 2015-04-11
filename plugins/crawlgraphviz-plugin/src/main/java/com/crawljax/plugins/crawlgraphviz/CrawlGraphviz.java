package com.crawljax.plugins.crawlgraphviz;

import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.state.StateVertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by soh on 10/04/15.
 */
public class CrawlGraphviz implements OnNewStatePlugin {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlGraphviz.class);

    @Override
    public void onNewState(CrawlerContext context, StateVertex newState) {
        LOG.info("onNewState called");
    }
}
