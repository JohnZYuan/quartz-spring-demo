import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import jobs.HelloJob;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @文件名称： QuartzTest.java
 * @文件路径： 
 * @功能描述： TODO
 * @作者： yuanzhen
 * @创建时间：2017年11月21日 上午10:29:33
 */

/**
 * @功能描述：
 * @创建人： yuanzhen
 * @创建时间： 2017年11月21日 上午10:29:33
 */
public class QuartzHelloTest {
	
	public static void main (String [] args) {
		
		try {
			//获取实例对象
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//启动并关闭
			scheduler.start();
			JobDetail job = newJob(HelloJob.class)
					.withIdentity("myJob", "group1")
					.build();
			
			//创建触发器
			Trigger trigger = newTrigger()
					.withIdentity("myTrigger", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(40)
							.repeatForever())
					.build();
			scheduler.scheduleJob(job,trigger);
			//线程休眠
			Thread.sleep(6000);
			scheduler.shutdown();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
