//evaluate(new File("dbclient.groovy"))
import RestClient
import DbClient
println "start test..."


db=new DbClient()
db.exist("job_1465357737625_0087")
rc=new RestClient()
rc.updateJob("1466575284051" as long,"job_1465357737625_0087")
//rc.getJobInfo("job_1465357737625_0052")

