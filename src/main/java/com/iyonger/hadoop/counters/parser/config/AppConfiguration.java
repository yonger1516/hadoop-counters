package com.iyonger.hadoop.counters.parser.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yongfu on 10/10/2016.
 */
@Configuration
public class AppConfiguration {
    @Autowired
    AppProperty property;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public InfluxDB influxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect("http://" + property.getInfluxdbServer() + ":" + property.getInfluxdbPort(), property.getInfluxdbUserName(), property.getInfluxdbPassword());
        influxDB.createDatabase(property.getDatabase());
        return influxDB;
    }
}
