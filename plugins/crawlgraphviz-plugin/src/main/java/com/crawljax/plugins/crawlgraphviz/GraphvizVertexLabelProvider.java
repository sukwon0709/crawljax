package com.crawljax.plugins.crawlgraphviz;

import com.crawljax.core.state.StateVertex;
import org.jgrapht.ext.VertexNameProvider;

/**
 * Created by soh on 11/04/15.
 */
public class GraphvizVertexLabelProvider implements VertexNameProvider<StateVertex> {
    @Override
    public String getVertexName(StateVertex stateVertex) {
        return stateVertex.getUrl();
    }
}
