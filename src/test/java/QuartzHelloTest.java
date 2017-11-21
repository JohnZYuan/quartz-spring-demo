import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import jobs.DumbJob;
import jobs.HelloJob;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @文件名称： QuartzTest.java
 * @文件路径： 
 * @功能描述： 文档教程测试
 * @作者： yuanzhen
 * @创建时间：2017年11月21日 上午10:29:33
 */

/**
 * @功能描述：文档教程测试
 * @创建人： yuanzhen
 * @创建时间： 2017年11月21日 上午10:29:33
 */
public class QuartzHelloTest {
	
	public static void main (String [] args) {
		
		try {
			//获取实例对象
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//启动调度
			scheduler.start();
			//定义两个job---helloJob无参---dumbJob带参
			JobDetail helloJob = newJob(HelloJob.class)
					.withIdentity("helloJob", "group1")
					.build();
			
			JobDetail dumbJob = newJob(DumbJob.class)
				      .withIdentity("dumbJob", "group1") // name "myJob", group "group1"
				      .usingJobData("jobSays", "Hello World!")
				      .usingJobData("myFloatValue", 3.141f)
				      .build();
			//创建触发器：每40秒触发一次
			Trigger trigger = newTrigger()
					.withIdentity("myTrigger", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(40)
							.repeatForever())
					.build();
			
			//调度Job
			//scheduler.scheduleJob(helloJob,trigger);
			scheduler.scheduleJob(dumbJob, trigger);
			//线程休眠
			Thread.sleep(60000);
			scheduler.shutdown();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
