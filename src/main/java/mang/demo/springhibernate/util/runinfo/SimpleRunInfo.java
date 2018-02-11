package mang.demo.springhibernate.util.runinfo;

public class SimpleRunInfo implements RunInfo {
	
	private static int downloadCount=0;
	private static RunInformation runInformation=RunInformation.getInstance();

	@Override
	public String getRunInfoStr() {
		String runDateInfo=runInformation.getRunDateInfo();
		String processCountInfo="download "+downloadCount+" files";
		String result=runDateInfo+" "+processCountInfo;
		return result;
	}
	
	public static void addDownloadCount(int addCount){
		downloadCount+=addCount;
	}

}
