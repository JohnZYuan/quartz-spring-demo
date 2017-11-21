/**
 * @文件名称： DumbJob.java
 * @文件路径： jobs
 * @功能描述： 带状态参数的Job （注入式）
 * @作者： yuanzhen
 * @创建时间：2017年11月21日 下午1:53:00
 */
package jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * @功能描述：
 * @创建人： yuanzhen
 * @创建时间： 2017年11月21日 下午1:53:00
 */
public class DumbJob implements Job{
	//参数必须与key值一致
	private String jobSays;
	private String triggerSays;
	private Float myFloatValue;
	private Double myDoubleValue;
	
	//设置set方法
	public void setMyFloatValue(Float myFloatValue) {
		this.myFloatValue = myFloatValue;
	}

	public Double getMyDoubleValue() {
		return myDoubleValue;
	}

	public void setMyDoubleValue(Double myDoubleValue) {
		this.myDoubleValue = myDoubleValue;
	}

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
		//获取信息：group和name 
		JobKey key = context.getJobDetail().getKey();
		System.out.println("My job's name and group:"+key.getName()+".."+key.getGroup());

		TriggerKey trKey = context.getTrigger().getKey();
		System.out.println("My trigger's name  and group:"+trKey.getName()+".."+key.getGroup());
		
		//获取参数
		//方法1 set方法，对应相应key值直接获取 最推荐
		System.err.println("JobDetail Instance "+ key + "of JobSays says: "+ jobSays +",and double val is: "+ myFloatValue);
		System.err.println("Trigger Instance "+ trKey + "of Trigger says: "+ triggerSays +",and double val is: "+ myDoubleValue);
		
		//方法2 分别从jobdetail和trigger中获得参数
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String _jobSays = dataMap.getString("jobSays");
		float _myFloatValue = dataMap.getFloat("myFloatValue");

		System.err.println("Job Instance " + key + " of DumbJob says: " + _jobSays + ", and val is: " + _myFloatValue);
		
		JobDataMap tDataMap = context.getTrigger().getJobDataMap();
		String _triggerSays = tDataMap.getString("triggerSays");
		Double _myDoubleValue = tDataMap.getDouble("myDoubleValue");
		
		System.err.println("Trigger Instance "+ trKey + "of Trigger says: "+ _triggerSays +",and double val is: "+ _myDoubleValue);
		
		//方法3 获取合并的data map(trigger里的Key会覆盖掉jobDetail的)
		JobDataMap mDataMap = context.getMergedJobDataMap();
		//此处省略N行代码
		
	}

}
