import RestClient
import DbClient

dbName="job_counters"
rc = new RestClient(dbName)
println "polling jobs to db..."
rc.pollingNewJob()

println "parsing new success jobs..."
db = new DbClient(dbName)
db.getSuccessJobs().each {
    k, v ->
        String ts="${k}"
        def jobId="${v}"

        rc.pullCountersByJobId(ts,jobId)
        rc.pullJobTimeCounter(jobId)

        def ts_double=ts as double
        rc.updateJob((long)ts_double,"${v}")

}

println "done."




