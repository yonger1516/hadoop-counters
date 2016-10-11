package com.iyonger.hadoop.counters.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * Created by yongfu on 10/10/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonRootName(value = "jobs")
public class Jobs {
    List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
