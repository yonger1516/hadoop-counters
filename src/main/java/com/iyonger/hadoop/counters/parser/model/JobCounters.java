package com.iyonger.hadoop.counters.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * Created by yongfu on 10/10/2016.
 */
@JsonIgnoreProperties
@JsonRootName(value = "jobCounters")
public class JobCounters {
    String id;
    List<CounterGroup> counterGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CounterGroup> getCounterGroup() {
        return counterGroup;
    }

    public void setCounterGroup(List<CounterGroup> counterGroup) {
        this.counterGroup = counterGroup;
    }
}
