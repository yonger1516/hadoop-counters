package com.iyonger.hadoop.counters.parser.restclient;

import com.iyonger.hadoop.counters.parser.config.AppProperty;
import com.iyonger.hadoop.counters.parser.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by yongfu on 10/10/2016.
 */
@Component
public class MrHistoryClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AppProperty property;

    static String schema = "http://";

   /* public MrHistoryClient(){
        prefix_path = schema + property.getHistoryServer() + ":" + property.getHistoryPort() + "/ws/v1/history";
    }*/

    public String prefix(){
        return schema + property.getHistoryServer() + ":" + property.getHistoryPort() + "/ws/v1/history";
    }

    public HistoryInfo getHistoryInfo(){
        return restTemplate.getForObject(prefix()+"/info",HistoryInfo.class);
    }

    public Jobs getJobList(){
        return restTemplate.getForObject(prefix()+"/mapreduce/jobs",Jobs.class);
    }

    public Job getJobDetails(String jobId){
        return restTemplate.getForObject(prefix()+"/mapreduce/jobs/"+jobId,Job.class);
    }


    public JobCounters getJobCounters(String jobId){
        return restTemplate.getForObject(prefix()+"/mapreduce/jobs/"+jobId+"/counters",JobCounters.class);
    }


}
