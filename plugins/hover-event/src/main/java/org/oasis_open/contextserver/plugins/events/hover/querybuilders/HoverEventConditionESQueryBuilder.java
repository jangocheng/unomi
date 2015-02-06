package org.oasis_open.contextserver.plugins.events.hover.querybuilders;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.oasis_open.contextserver.api.conditions.Condition;
import org.oasis_open.contextserver.persistence.elasticsearch.conditions.ConditionESQueryBuilder;
import org.oasis_open.contextserver.persistence.elasticsearch.conditions.ConditionESQueryBuilderDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by toto on 27/06/14.
*/
public class HoverEventConditionESQueryBuilder implements ConditionESQueryBuilder {

    public HoverEventConditionESQueryBuilder() {
    }

    public FilterBuilder buildFilter(Condition condition, Map<String, Object> context, ConditionESQueryBuilderDispatcher dispatcher) {
        List<FilterBuilder> filters = new ArrayList<FilterBuilder>();
        filters.add(FilterBuilders.termFilter("eventType", "hover"));
        String targetId = (String) condition.getParameterValues().get("targetId");
        String targetPath = (String) condition.getParameterValues().get("targetPath");

        if (targetId != null && targetId.trim().length() > 0) {
            filters.add(FilterBuilders.termFilter("target.itemId", targetId));
        } else if (targetPath != null && targetPath.trim().length() > 0) {
            filters.add(FilterBuilders.termFilter("target.properties.pageInfo.pagePath", targetPath));
        } else {
            filters.add(FilterBuilders.termFilter("target.itemId", ""));
        }
        return FilterBuilders.andFilter(filters.toArray(new FilterBuilder[filters.size()]));
    }
}
