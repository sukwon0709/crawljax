package com.crawljax.plugins.crawlgraphviz;

import com.crawljax.core.CrawlSession;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.ExitNotifier;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.plugin.PostCrawlingPlugin;
import com.crawljax.core.state.StateFlowGraph;
import com.crawljax.core.state.StateVertex;
import org.jgrapht.ext.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by soh on 10/04/15.
 */
public class CrawlGraphviz implements OnNewStatePlugin, PostCrawlingPlugin {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlGraphviz.class);

    @Override
    public void onNewState(CrawlerContext context, StateVertex newState) {
        LOG.info("onNewState called");
    }

    @Override
    public void postCrawling(CrawlSession session, ExitNotifier.ExitStatus exitReason) {
        LOG.info("postCrawling called");
        // crawljax already uses JGraphT to store stateflow graph.
        StateFlowGraph sfg = session.getStateFlowGraph();
        try {
            Writer writer = new FileWriter("crawl.dot");
            GraphvizVertexNameProvider vertexNameProvider = new GraphvizVertexNameProvider();
            GraphvizVertexLabelProvider vertexLabelProvider = new GraphvizVertexLabelProvider();
            GraphvizEdgeNameProvider edgeNameProvider = new GraphvizEdgeNameProvider();
            DOTExporter dotExporter = new DOTExporter(vertexNameProvider, vertexLabelProvider, edgeNameProvider);
            dotExporter.export(writer, sfg.getInternalGraph());
            LOG.info("successfully exported crawl.dot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
