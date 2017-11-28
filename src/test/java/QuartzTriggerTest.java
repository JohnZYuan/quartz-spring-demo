import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import jobs.HelloJob;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;

/**
 * @文件名称： QuartzTriggerTest.java
 * @文件路径： 
 * @功能描述： Trigger相关测试
 * @作者： yuanzhen
 * @创建时间：2017年11月28日 下午2:08:31
 */

/**
 * @功能描述：Trigger相关测试
 * @创建人： yuanzhen
 * @创建时间： 2017年11月28日 下午2:08:31
 */
public class QuartzTriggerTest {
	
	public static void main(String[] args) {
		try{
			//创建一个JobDetail实例
			JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
					.withIdentity("helloJob", "group1")
					.build();
			
			//获取距离当前时间3秒后的时间
			Date startDate = new Date();
			startDate.setTime(startDate.getTime() + 3000);
			
			//距离当前时间6秒后结束
			Date endDate = new Date();
			endDate.setTime(endDate.getTime()+6000);
			
			//设置startAt endAt
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("myTrigger","group1")
					.startAt(startDate)
					.endAt(endDate)
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(2).repeatForever())
					.build();
			
			Date sStartDate = new Date ();
			sStartDate.setTime(sStartDate.getTime()+4000);
			//simpleTrigger距离当前时间4秒钟后首次执行，之后每两秒钟执行一次,再重复执行3次
			SimpleTrigger sTrigger = (SimpleTrigger)TriggerBuilder
					.newTrigger()
					.withIdentity("sTrigger", "group2")
					.startAt(sStartDate)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(2)
							.withRepeatCount(3))
					.build();
			
			//获取实例对象(工厂模式)
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			//scheduler.scheduleJob(helloJob,trigger);
			scheduler.scheduleJob(helloJob,sTrigger);
			scheduler.start();
		}catch (Exception e) {
			
		}
	}
	
}
