package com.iyonger.hadoop.counters.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * Created by yongfu on 10/10/2016.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonRootName(value = "job")
public class Job {
    String id;
    String name;
    String queue;
    String user;
    String state;
    String diagnostics;
    long submitTime;
    long startTime;
    long finishTime;
    int mapsTotal;
    int mapsCompleted;
    int reducesTotal;
    int reducesCompleted;
    boolean uberized;
    long avgMapTime;
    long avgReduceTime;
    long avgShuffleTime;
    long avgMergeTime;
    int failedReduceAttempts;
    int killedReduceAttempts;
    int successfulReduceAttempts;
    int failedMapAttempts;
    int killedMapAttempts;
    int successfulMapAttempts;
    List<Acls> acls;

    //additional from run info
    @JsonIgnore
    String runId;
    @JsonIgnore
    String throughput;

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getThroughput() {
        return throughput;
    }

    public void setThroughput(String throughput) {
        this.throughput = throughput;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(String diagnostics) {
        this.diagnostics = diagnostics;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public int getMapsTotal() {
        return mapsTotal;
    }

    public void setMapsTotal(int mapsTotal) {
        this.mapsTotal = mapsTotal;
    }

    public int getMapsCompleted() {
        return mapsCompleted;
    }

    public void setMapsCompleted(int mapsCompleted) {
        this.mapsCompleted = mapsCompleted;
    }

    public int getReducesTotal() {
        return reducesTotal;
    }

    public void setReducesTotal(int reducesTotal) {
        this.reducesTotal = reducesTotal;
    }

    public int getReducesCompleted() {
        return reducesCompleted;
    }

    public void setReducesCompleted(int reducesCompleted) {
        this.reducesCompleted = reducesCompleted;
    }

    public boolean isUberized() {
        return uberized;
    }

    public void setUberized(boolean uberized) {
        this.uberized = uberized;
    }

    public long getAvgMapTime() {
        return avgMapTime;
    }

    public void setAvgMapTime(long avgMapTime) {
        this.avgMapTime = avgMapTime;
    }

    public long getAvgReduceTime() {
        return avgReduceTime;
    }

    public void setAvgReduceTime(long avgReduceTime) {
        this.avgReduceTime = avgReduceTime;
    }

    public long getAvgShuffleTime() {
        return avgShuffleTime;
    }

    public void setAvgShuffleTime(long avgShuffleTime) {
        this.avgShuffleTime = avgShuffleTime;
    }

    public long getAvgMergeTime() {
        return avgMergeTime;
    }

    public void setAvgMergeTime(long avgMergeTime) {
        this.avgMergeTime = avgMergeTime;
    }

    public int getFailedReduceAttempts() {
        return failedReduceAttempts;
    }

    public void setFailedReduceAttempts(int failedReduceAttempts) {
        this.failedReduceAttempts = failedReduceAttempts;
    }

    public int getKilledReduceAttempts() {
        return killedReduceAttempts;
    }

    public void setKilledReduceAttempts(int killedReduceAttempts) {
        this.killedReduceAttempts = killedReduceAttempts;
    }

    public int getSuccessfulReduceAttempts() {
        return successfulReduceAttempts;
    }

    public void setSuccessfulReduceAttempts(int successfulReduceAttempts) {
        this.successfulReduceAttempts = successfulReduceAttempts;
    }

    public int getFailedMapAttempts() {
        return failedMapAttempts;
    }

    public void setFailedMapAttempts(int failedMapAttempts) {
        this.failedMapAttempts = failedMapAttempts;
    }

    public int getKilledMapAttempts() {
        return killedMapAttempts;
    }

    public void setKilledMapAttempts(int killedMapAttempts) {
        this.killedMapAttempts = killedMapAttempts;
    }

    public int getSuccessfulMapAttempts() {
        return successfulMapAttempts;
    }

    public void setSuccessfulMapAttempts(int successfulMapAttempts) {
        this.successfulMapAttempts = successfulMapAttempts;
    }

    public List<Acls> getAcls() {
        return acls;
    }

    public void setAcls(List<Acls> acls) {
        this.acls = acls;
    }

    @Override
    public String toString(){
        return "id:"+id+",name:"+name;
    }
}
