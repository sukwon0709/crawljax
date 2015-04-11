package com.crawljax.plugins.crawlgraphviz;

import com.crawljax.core.state.Eventable;
import org.jgrapht.ext.EdgeNameProvider;

/**
 * Created by soh on 11/04/15.
 */
public class GraphvizEdgeNameProvider implements EdgeNameProvider<Eventable> {
    @Override
    public String getEdgeName(Eventable eventable) {
        String eventType = eventable.getEventType().name();
        return eventType + " " + eventable.getIdentification().getValue();
    }
}
