package mang.demo.springhibernate.util.runinfo;

public class RunInfoUtil {
	public static RunInfo runinfo;
	
	public static String getRunInfoStr(){
		if(runinfo!=null){
			return runinfo.getRunInfoStr();			
		}
		
		return "";
	}
}
