package com.crawljax.plugins.crawlgraphviz;

import com.crawljax.core.state.StateVertex;
import org.jgrapht.ext.VertexNameProvider;

/**
 * Created by soh on 11/04/15.
 */
public class GraphvizVertexNameProvider implements VertexNameProvider<StateVertex> {
    @Override
    public String getVertexName(StateVertex stateVertex) {
        return stateVertex.getName();
    }
}
