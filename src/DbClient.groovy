import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import org.influxdb.dto.BatchPoints
import org.influxdb.dto.Point
import java.util.concurrent.TimeUnit
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult

class DbClient {

    InfluxDB influxDB
    BatchPoints batchPoints
    String dbName

    public DbClient(String name) {
        influxDB = InfluxDBFactory.connect("http://10.239.10.6:8086", "root", "root")
        dbName=name
        influxDB.createDatabase(dbName)
    }

    void initBatch(String tag) {
        batchPoints = BatchPoints
                .database(dbName)
                .tag("group", tag)
                .retentionPolicy("default")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build()
    }


    void saveJob(def v1, def v2, def v3, def v4) {
        String mName = "JOB_LIST"
        println "saving job :" + v2
        Point point = Point.measurement(mName)
                .time(v1, TimeUnit.MILLISECONDS)
                .addField("jobId", v2)
                .addField("state", v3)
                .addField("isParsed", v4)
                .build()
        batchPoints.point(point)
    }
    void saveJobTimeCounter(long ts, int v1, int v2, int v3, int v4) {
        String mName = "JOB_INFO"
        Point point = Point.measurement(mName)
                .time(ts, TimeUnit.MILLISECONDS)
                .addField("avgMapTime", v1)
                .addField("avgReduceTime", v2)
                .addField("avgShuffleTime", v3)
                .addField("avgMergeTime", v4)
                .build()
        batchPoints.point(point)
    }

    boolean exist(def id) {
        Query q = new Query("select * from JOB_LIST where jobId='" + id + "'", dbName)
        QueryResult res = influxDB.query(q)
        if (res.getResults().get(0).getSeries() == null) {
            return false
        } else {
            return true
        }

    }

    Map getSuccessJobs() {
        Query q = new Query("select * from JOB_LIST where state='SUCCEEDED' and isParsed='false'", dbName)
        QueryResult res = influxDB.query(q, TimeUnit.MILLISECONDS)

        if (res.getResults().get(0).getSeries() == null) {
            return new HashMap<String, String>()
        }
        Map map = new HashMap<String, String>()
        for (int i = 0; i < res.getResults().get(0).getSeries().get(0).getValues().size(); i++) {
            map.put(res.getResults().get(0).getSeries().get(0).getValues().get(i).get(0), res.getResults().get(0).getSeries().get(0).getValues().get(i).get(3))
        }
        return map
    }

    void saveCounters(String mName, Map counters) {
        Point.Builder builder = Point.measurement(mName)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        counters.each { k, v ->
            builder.addField("${k}", "${v}")
            println "${k},${v}"
        }

        batchPoints.point(builder.build())

    }

    void saveCounters(def mName, String ts, def v1, def v2, def v3) {
        def long_ts = ts as double
        Point point = Point.measurement(mName)
                .time((long) long_ts, TimeUnit.MILLISECONDS)
                .addField("mapCounterValue", v1)
                .addField("reduceCounterValue", v2)
                .addField("totalCounterValue", v3)
                .build()
        batchPoints.point(point)
    }

    void flush() {
        influxDB.write(batchPoints)
    }


}
