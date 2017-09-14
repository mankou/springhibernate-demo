package mang.demo.springhibernate.util;

public class MyVersion {
	
	/**
	 * 当前版本
	 * */
	public static final Version CURRENT_VERSION=Version.V0_0_1_SNAPSHOT;
	
	/**
	 * 当前版本号
	 * */
    public static final int CURRENT_VERSION_CODE = CURRENT_VERSION.ordinal();
    
    /**
     * 当前版本名
     * */
    public static final String CURRENT_VERSION_NAME = CURRENT_VERSION.name();
    
    /**
     * 当前版本 发布日期
     * */
    public static final String CURRENT_VERSION_RELEASE_DATE = CURRENT_VERSION.getReleaseDate();
    
    
    /**
     * 获取当前版本描述
     * */
    public static String getCurrentVersionDesc(){
    	String desc=CURRENT_VERSION_NAME+"-"+CURRENT_VERSION_RELEASE_DATE;
    	return desc;
    }
    

    public static String getVersionDesc(int value) {
        int length = Version.values().length;
        if (value >= length) {
            return Version.values()[length - 1].name();
        }

        return Version.values()[value].name();
    }

    public static Version value2Version(int value) {
        int length = Version.values().length;
        if (value >= length) {
            return Version.values()[length - 1];
        }

        return Version.values()[value];
    }

    
    public enum Version {
    	V0_0_1_SNAPSHOT("2017-09-12"),   	//第1版
        HIGHER_VERSION("higher_version");
        
        public String releaseDate; 
        
        private Version(String releaseDate){ 
              this.releaseDate = releaseDate; 
        }

		public String getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
		}
        
    }
}
