/**
 * @文件名称： HelloJob.java
 * @文件路径： jobs
 * @功能描述： TODO
 * @作者： yuanzhen
 * @创建时间：2017年11月21日 下午2:03:38
 */
package jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @功能描述：
 * @创建人： yuanzhen
 * @创建时间： 2017年11月21日 下午2:03:38
 */
public class HelloJob implements Job{

	/**
	 * @功能描述：第一个Job
	 * @参数说明：@param arg0
	 * @参数说明：@throws JobExecutionException
	 * @作者： yuanzhen
	 * @创建时间：2017年11月21日 下午2:04:32
	 * @接口方法：@see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.err.println("HelloJob! This Job is executing");
	}
}
