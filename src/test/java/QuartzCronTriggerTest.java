import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import jobs.HelloJob;


import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

/**
 * @文件名称： QuartzCronTriggerTest.java
 * @文件路径： 
 * @功能描述： CronTriggerTest 基于日历的任务调度器
 * @作者： yuanzhen
 * @创建时间：2017年11月28日 下午3:23:51
 */

/**
 * @功能描述：CronTriggerTest 
 * Cron表达式 [秒] [分] [小时] [日] [月] [周] [年]
 * 字 段		是否必填		允许值			允许的特殊字符
 *  秒		     是			0~59			  , - * /
 *  分		     是			0~59			  , - * /
 * 小时		     是			0~23			  , - * /
 *  日		     是			1~31			  , - * ? / L W C
 *  月		     是			1~12或者JAN-DEC    , - * /
 *  周		     是			1~7或者SUN-SAT     , - * ? / L C #
 *  年		     否			empty,1970~2099   , - * /
 * @创建人： yuanzhen
 * @创建时间： 2017年11月28日 下午3:23:51
 */
public class QuartzCronTriggerTest {

	public static void main(String[] args) {
		try{
			//创建一个JobDetail实例
			JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
					.withIdentity("helloJob", "group1")
					.build();
			
			String cronStr = "";
			//每秒钟触发一次
			cronStr = "* * * * * * *";
			//1.2017年内每天的10点15分触发一次
			//cronStr = "0 15 10 * * * 2017";
			//2.每天的14点整至14点59分55秒，以及18点整至18点59分55秒，每5秒触发一次
			//cronStr = "0/5 * 14,18 * * * *";
			//3.每月周一至周五的10点15分触发一次
			//cronStr = "0 15 10 * * 2-6 *";
			//4.每月最后一天的10点15分触发一次
			//cronStr = "0 15 10 L * ? *";
			//5.每月第三个周五的10点15分触发一次
			//cronStr = "0 15 10 ? * 6#3 *";
			
			CronTrigger cTrigger = (CronTrigger) TriggerBuilder
					.newTrigger()
					.withIdentity("cTrigger","group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(cronStr))
					.build();
			
			//获取实例对象（工厂模式）
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.scheduleJob(helloJob,cTrigger);
			scheduler.start();
		}catch (Exception e) {
		}
	}
}
