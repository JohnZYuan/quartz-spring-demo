/**
 * @文件名称： HelloJob.java
 * @文件路径： jobs
 * @功能描述： TODO
 * @作者： yuanzhen
 * @创建时间：2017年11月21日 下午2:03:38
 */
package jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;

/**
 * @功能描述：
 * @创建人： yuanzhen
 * @创建时间： 2017年11月21日 下午2:03:38
 */
public class HelloJob implements Job{

	/**
	 * @功能描述：第一个Job
	 * @参数说明：@param context
	 * @参数说明：@throws JobExecutionException
	 * @作者： yuanzhen
	 * @创建时间：2017年11月21日 下午2:04:32
	 * @接口方法：@see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current Exec Time Is:" + sf.format(date));
//		Trigger currentTrigger = context.getTrigger();
//		System.out.println("Start Time Is:" + sf.format(currentTrigger.getStartTime()));
//		System.out.println("End Time Is:" + sf.format(currentTrigger.getEndTime()));
		System.err.println("HelloJob! This Job is executing");
//		JobKey jobKey = currentTrigger.getJobKey();
//		System.out.println("JobKey info---" + "jobName:"+jobKey.getName()
//		+ "jobGroup:"+jobKey.getGroup());
	}
}
