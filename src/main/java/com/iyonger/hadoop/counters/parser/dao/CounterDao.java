package com.iyonger.hadoop.counters.parser.dao;

import com.iyonger.hadoop.counters.parser.config.AppProperty;
import com.iyonger.hadoop.counters.parser.model.Counter;
import com.iyonger.hadoop.counters.parser.model.CounterGroup;
import com.iyonger.hadoop.counters.parser.model.Job;
import com.iyonger.hadoop.counters.parser.model.JobCounters;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by yongfu on 10/10/2016.
 */
@Component
public class CounterDao {

    @Autowired
    InfluxDB influxDB;

    @Autowired
    AppProperty property;

    BatchPoints batchPoints;


    public BatchPoints getBatchPoints(){
        if(null==batchPoints){
            batchPoints = BatchPoints
                    .database(property.getDatabase())
                    .tag("async", "true")
                    .retentionPolicy("autogen")
                    .consistency(InfluxDB.ConsistencyLevel.ALL)
                    .build();
        }

        return batchPoints;
    }

    public void saveJob(Job job) {
        Point point = Point.measurement("job")
                .time(job.getSubmitTime(), TimeUnit.MILLISECONDS)
                .addField("jobId", job.getId())
                .addField("name",job.getName())
                .addField("state", job.getState())
                .addField("submitTime",job.getSubmitTime())
                .addField("mapsTotal",job.getMapsTotal())
                .addField("reducesTotal",job.getReducesTotal())
                .addField("avgMapTime",job.getAvgMapTime())
                .addField("avgShuffleTime",job.getAvgShuffleTime())
                .addField("avgMergeTime",job.getAvgMergeTime())
                .addField("avgReduceTime",job.getAvgReduceTime())

                .addField("runId",job.getRunId())
                .addField("throughput",job.getThroughput())
                .build();
        getBatchPoints().point(point);
        influxDB.write(batchPoints);
    }

    public void saveJobCounter(JobCounters counters) {
        for (CounterGroup cg : counters.getCounterGroup()) {
            for (Counter c : cg.getCounter()) {
                Point p = Point.measurement(c.getName())
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .addField("job_id", counters.getId())
                        .addField("map_count_value", c.getMapCounterValue())
                        .addField("reduce_count_value", c.getReduceCounterValue())
                        .addField("total_count_value", c.getTotalCounterValue())
                        .build();
                getBatchPoints().point(p);
            }
        }
        influxDB.write(batchPoints);

    }

    public void flush() {
        influxDB.write(batchPoints);
    }

}
