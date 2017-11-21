import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.DailyCalendar;
import org.quartz.impl.calendar.HolidayCalendar;

import jobs.DumbJob;
import jobs.HelloJob;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @文件名称： HolidayCalendarTest.java
 * @文件路径： 
 * @功能描述： 排除指定日期的calendar测试
 * @作者： yuanzhen
 * @创建时间：2017年11月22日 上午9:44:06
 */

/**
 * @功能描述： 排除指定日期的calendar测试
 * @创建人： yuanzhen
 * @创建时间： 2017年11月22日 上午9:44:06
 */
public class HolidayCalendarTest {
	
	public static void main(String[] args) {
		
		//获取实例对象
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			//声明一个节日日历
			HolidayCalendar cal = new HolidayCalendar();
			DateFormat dateFormat = new SimpleDateFormat("MM-dd");
			Date birthDay = new Date();
			try {
				birthDay = dateFormat.parse("08-28");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//排除掉指定日期
			cal.addExcludedDate(birthDay);
			//将日历添加到调度器
			scheduler.addCalendar("birthDay", cal, false, false );
			
			//声明job
			JobDetail helloJob = newJob(HelloJob.class)
					.withIdentity("helloJob", "group1")
					.build();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
