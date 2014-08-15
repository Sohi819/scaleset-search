package com.scaleset.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vividsolutions.jts.geom.Envelope;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"query", "totalItems", "aggregations", "items"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Results<T> {

    private List<AggregationResults> aggs = new ArrayList<>();
    private List<T> items = new ArrayList<>();
    private Query query;
    private Integer totalItems = 0;
    private Envelope bbox;

    /* For Jackson */
    protected Results() {
    }

    public Results(Query query, List<AggregationResults> aggs, List<T> items, Integer totalItems) {
        this(query, aggs, items, totalItems, null);
    }

    public Results(Query query, List<AggregationResults> aggs, List<T> items, Integer totalItems, Envelope bbox) {
        this.query = query;
        if (aggs != null) {
            this.aggs.addAll(aggs);
        }
        this.items.addAll(items);
        this.totalItems = totalItems;
        this.bbox = bbox;
    }

    public Results(Query query, List<T> items) {
        this.query = query;
        this.items.addAll(items);
    }

    public Envelope getBbox() {
        return bbox;
    }

    public AggregationResults getAgg(String name) {
        for (AggregationResults agg : aggs) {
            if (name.equals(agg.getName())) {
                return agg;
            }
        }
        return null;
    }

    public List<AggregationResults> getAggs() {
        return aggs;
    }

    public List<T> getItems() {
        return items;
    }

    public Query getQuery() {
        return query;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

}
