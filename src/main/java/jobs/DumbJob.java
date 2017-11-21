/**
 * @文件名称： DumbJob.java
 * @文件路径： jobs
 * @功能描述： TODO
 * @作者： yuanzhen
 * @创建时间：2017年11月21日 下午1:53:00
 */
package jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * @功能描述：
 * @创建人： yuanzhen
 * @创建时间： 2017年11月21日 下午1:53:00
 */
public class DumbJob implements Job{

	/**
	 * @构造方法：DumbJob
	 * @参数说明：
	 * @功能描述：基础测试Job
	 */
	public DumbJob() {
	}
	
	/**
	 * @功能描述：基础测试Job
	 * @参数说明：@param arg0
	 * @参数说明：@throws JobExecutionException
	 * @作者： yuanzhen
	 * @创建时间：2017年11月21日 下午1:53:27
	 * @接口方法：@see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey key = context.getJobDetail().getKey();
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		
		String jobSays = dataMap.getString("jobSays");
		float myFloatValue = dataMap.getFloat("myFloatValue");

		System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
	}

}
