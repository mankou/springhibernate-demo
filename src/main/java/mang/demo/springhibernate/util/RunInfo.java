package mang.demo.springhibernate.util;

import java.util.Date;

import mang.util.common.DateUtil;

public class RunInfo {

	private static RunInfo instance = new RunInfo();

	public static RunInfo getInstance() {
		return instance;
	}
	
	/**
	 * 获取运行信息字符串
	 * */
	public static String getRunInfoStr(){
		RunInfo runInfo=RunInfo.getInstance();
		String runInfoStr=runInfo.getInfo();
		return runInfoStr;
	}
	
	private RunInfo() {
		startTime=new Date();
		runCount=0L;
	}

	private Date startTime;
	
	private Long runCount;

	
	private String getInfo(){
		Date endTime=new Date();
		String startTimeStr=DateUtil.getDateString(startTime);
		String runTimeStr=DateUtil.computeTimeInterval(endTime, startTime, "en");
		String str="runTime: "+runTimeStr+"("+startTimeStr+")"+" runCount: "+ runCount+"";
		return str;
	}
	
	
	public void addRunCount(){
		this.runCount++;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Long getRunCount() {
		return runCount;
	}

	public void setRunCount(Long runCount) {
		this.runCount = runCount;
	}
	
}
