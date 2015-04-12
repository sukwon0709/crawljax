package com.crawljax.plugins.crawlgraphviz;

import com.crawljax.core.state.Eventable;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.ext.EdgeNameProvider;

/**
 * Created by soh on 11/04/15.
 */
public class GraphvizEdgeNameProvider implements EdgeNameProvider<Eventable> {
    @Override
    public String getEdgeName(Eventable eventable) {
        String eventType = eventable.getEventType().name();
        String elementTag = eventable.getElement().getTag();
        String elementId = eventable.getElement().getElementId();
        String elementText = StringUtils.left(eventable.getElement().getText(), 10);
        String edgeName = "<" + elementTag + " id=" + elementId + " name=" + elementText + ">";
        return eventType + " " + edgeName;
    }
}
