import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import jobs.DumbJob;
import jobs.HelloJob;

import org.quartz.JobBuilder;
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
			//获取实例对象（工厂模式）
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//定义两个jobDetail实例与job类绑定---helloJob无参---dumbJob带参（Build模式）
			JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
					.withIdentity("helloJob", "group1")
					.build();
			
			System.out.println("helloJobDetail's name:"+helloJob.getKey().getName());
			System.out.println("helloJobDetail's group:"+helloJob.getKey().getGroup());
			System.out.println("helloJobDetail's group:"+helloJob.getJobClass().getName());
			
			JobDetail dumbJob = JobBuilder.newJob(DumbJob.class)
				      .withIdentity("dumbJob", "group1") // name "myJob", group "group1"
				      .usingJobData("jobSays", "Hello World!")
				      .usingJobData("myFloatValue", 3.141f)
				      .build();
			
			//创建触发器：立即执行，每40秒触发一次，无止的重复下去---Trigger的组和Job的组无关
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("myTrigger", "group1")
					.usingJobData("triggerSays", "Hello Trigger")
					.usingJobData("myDoubleValue", 2.14d)
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(4)
							.repeatForever())
					.build();

			//调度Job，将JobDetail和Trigger绑定在一起
			//scheduler.scheduleJob(helloJob,trigger);
			scheduler.scheduleJob(dumbJob, trigger);
			
			//启动调度
			scheduler.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
