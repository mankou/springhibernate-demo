package mang.demo.springhibernate.util.runinfo;

import java.util.Date;

import mang.util.common.DateUtil;

public class RunInformation {
	private static RunInformation instance = new RunInformation();

	private Date startTime;

	public static RunInformation getInstance() {
		return instance;
	}

	private RunInformation() {
		startTime = new Date();
	}

	public String getRunDateInfo() {
		Date endTime = new Date();
		String startTimeStr = DateUtil.getDateString(startTime);
		String runTimeStr = DateUtil.computeTimeInterval(endTime, startTime, "en");
		String str = "runTime: " + runTimeStr + "(" + startTimeStr + ")";
		return str;
	}

	public Date getStartTime() {
		return startTime;
	}

}
