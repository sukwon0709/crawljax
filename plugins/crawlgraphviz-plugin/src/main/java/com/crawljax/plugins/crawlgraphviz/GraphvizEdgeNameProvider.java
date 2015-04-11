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
        String elementTag = eventable.getElement().getTag();
        String elementText = eventable.getElement().getText();
        String elementId = eventable.getElement().getElementId();
        String elementName = "<" + elementTag + " id=" + elementId + " text=" + elementText + ">";
        return eventType + " " + elementName;
    }
}
