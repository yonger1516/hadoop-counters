package com.iyonger.hadoop.counters.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by yongfu on 10/11/2016.
 */
@JsonIgnoreProperties
@JsonRootName(value = "historyInfo")
public class HistoryInfo {
    long startedOn;
    String hadoopVersion;
    String hadoopBuildVersion;
    String hadoopVersionBuiltOn;

    public long getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(long startedOn) {
        this.startedOn = startedOn;
    }

    public String getHadoopVersion() {
        return hadoopVersion;
    }

    public void setHadoopVersion(String hadoopVersion) {
        this.hadoopVersion = hadoopVersion;
    }

    public String getHadoopBuildVersion() {
        return hadoopBuildVersion;
    }

    public void setHadoopBuildVersion(String hadoopBuildVersion) {
        this.hadoopBuildVersion = hadoopBuildVersion;
    }

    public String getHadoopVersionBuiltOn() {
        return hadoopVersionBuiltOn;
    }

    public void setHadoopVersionBuiltOn(String hadoopVersionBuiltOn) {
        this.hadoopVersionBuiltOn = hadoopVersionBuiltOn;
    }
}
