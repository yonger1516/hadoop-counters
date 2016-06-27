import groovy.json.JsonSlurper
import DbClient

class RestClient {
    String host = '10.239.44.158'
    String port = '19888'
    String schema = 'http://'
    String prefix_path = schema + host + ":" + port + '/ws/v1/history'
    DbClient dbClient

    public RestClient(String dbName) {
        //init influxdb client
        dbClient = new DbClient(dbName)
    }

    String getHistoryInfo() {
        def path = prefix_path + "/info"
        return new URL(path)
                .text
    }

    String getAllJobs() {
        String path = prefix_path + "/mapreduce/jobs"
        def obj_jobs = getJsonObj(path)

        //println obj_jobs.jobs
        int len = obj_jobs.jobs.job.size()
        println "size:" + len
        for (int i = 0; i < len; i++) {
            println obj_jobs.jobs.job[i]

        }
    }

    void pollingNewJob() {
        String path = prefix_path + "/mapreduce/jobs"
        def obj_jobs = getJsonObj(path)

        if (null == obj_jobs) {
            println "no jobs to be found"
            return
        }
        dbClient.initBatch("jobs")
        int len = obj_jobs.jobs.job.size()
        for (int i = 0; i < len; i++) {
            def job = obj_jobs.jobs.job[i]
            if (!dbClient.exist(job.id)) {
                println "pushing new job to db ...," + job.id
                dbClient.saveJob(job.submitTime, job.id, job.state, "false")
            }
        }
        dbClient.flush()

    }

    String pullJobTimeCounter(String id) {
        def path = prefix_path + "/mapreduce/jobs/" + id
        def obj
        try {
            obj = getJsonObj(path)
        } catch (Exception e) {
            println e
            return
        }
        println "store info of job: " + obj.job.id
        dbClient.initBatch("job_info")
        dbClient.saveJobInfo(obj.job.submitTime, obj.job.avgMapTime, obj.job.avgReduceTime, obj.job.avgShuffleTime, obj.job.avgMergeTime)
        dbClient.flush()
    }

    String pullCountersByJobId(String ts, String id) {
        println "parsing job " + id
        def path = prefix_path + "/mapreduce/jobs/" + id + "/counters"
        println path
        def counters_obj = getJsonObj(path)
        if (null == counters_obj) {
            println "no counters to be found"
            return
        }

        def job_counters_size = counters_obj.jobCounters.size()
        for (int i = 0; i <= job_counters_size; i++) {
            parseMeasurements(ts, counters_obj.jobCounters.counterGroup[i])
        }

    }

    void parseMeasurements(String ts, def counter_group) {
        int size = counter_group.size()
        //println "parsing group: " + counter_group.counterGroupName
        def list = counter_group.counterGroupName.tokenize(".")
        def tag = list.get(list.size() - 1)
        dbClient.initBatch(tag)
        Map counters = parseCounters(ts, counter_group.counter)
        //must flush after each batch
        dbClient.flush()
    }

    void parseCounters(String ts, def counters) {
        int size = counters.size()
        for (int i = 0; i < size; i++) {
            //println "counter : "+counters[i].name
            dbClient.saveCounters(counters[i].name, ts, counters[i].mapCounterValue, counters[i].reduceCounterValue, counters[i].totalCounterValue)
        }
    }

    void updateJob(def ts, def id) {
        dbClient.initBatch("jobs")
        dbClient.saveJob(ts, id, "SUCCEEDED", "true")
        dbClient.flush()
    }

    Object getJsonObj(String url) {
        try {
            def json_text = new URL(url)
                    .text
            def jsonSlurper = new JsonSlurper()
            return jsonSlurper.parseText(json_text)
        } catch (Exception e) {
            println e
            return null
        }


    }
}