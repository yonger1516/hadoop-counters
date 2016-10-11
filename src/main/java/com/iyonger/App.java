package com.iyonger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyonger.hadoop.counters.parser.dao.CounterDao;
import com.iyonger.hadoop.counters.parser.model.Job;
import com.iyonger.hadoop.counters.parser.model.JobCounters;
import com.iyonger.hadoop.counters.parser.restclient.MrHistoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    @Autowired
    MrHistoryClient mrHistoryClient;

    @Autowired
    CounterDao counterDao;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        /*System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            logger.info("get history info");
            logger.info(new ObjectMapper().writeValueAsString(mrHistoryClient.getHistoryInfo()));

            if (args.length<1){
                logger.info("not enough parameters, exit");
                printUsage();
                System.exit(0);
            }

            String jobId=args[0];
            logger.info("start parsing job:"+jobId);

            Job newJob=mrHistoryClient.getJobDetails(jobId);
            logger.debug("job-->"+new ObjectMapper().writeValueAsString(newJob));


            String runId=null==args[1]?"":args[1];
            String throughput=null==args[2]?"":args[2];

            newJob.setRunId(runId);
            newJob.setThroughput(throughput);

            counterDao.saveJob(newJob);

            JobCounters counters=mrHistoryClient.getJobCounters(jobId);
            counterDao.saveJobCounter(counters);

            logger.info("done.");
            System.exit(1);
        };
    }

    private void printUsage() {
        logger.info("java -jar hadoop-counter-parser.jar [jobId] [runId] [throughput]");
    }


}
