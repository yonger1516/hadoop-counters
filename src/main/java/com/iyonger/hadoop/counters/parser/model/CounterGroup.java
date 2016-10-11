package com.iyonger.hadoop.counters.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yongfu on 10/10/2016.
 */
@JsonIgnoreProperties
public class CounterGroup {
    String counterGroupName;
    List<Counter> counter;

    public String getCounterGroupName() {
        return counterGroupName;
    }

    public void setCounterGroupName(String counterGroupName) {
        this.counterGroupName = counterGroupName;
    }

    public List<Counter> getCounter() {
        return counter;
    }

    public void setCounter(List<Counter> counter) {
        this.counter = counter;
    }
}
