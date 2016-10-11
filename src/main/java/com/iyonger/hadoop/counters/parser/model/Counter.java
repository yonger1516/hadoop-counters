package com.iyonger.hadoop.counters.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yongfu on 10/10/2016.
 */
@JsonIgnoreProperties
public class Counter {
    String name;
    long reduceCounterValue;
    long mapCounterValue;
    long totalCounterValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getReduceCounterValue() {
        return reduceCounterValue;
    }

    public void setReduceCounterValue(long reduceCounterValue) {
        this.reduceCounterValue = reduceCounterValue;
    }

    public long getMapCounterValue() {
        return mapCounterValue;
    }

    public void setMapCounterValue(long mapCounterValue) {
        this.mapCounterValue = mapCounterValue;
    }

    public long getTotalCounterValue() {
        return totalCounterValue;
    }

    public void setTotalCounterValue(long totalCounterValue) {
        this.totalCounterValue = totalCounterValue;
    }
}
