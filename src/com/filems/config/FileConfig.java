package com.filems.config;

public class FileConfig {
	
	public static final long  MAX_FILE_SIZE_1MB=1048576;
	public static final long  MAX_FILE_SIZE_500MB=524288000;
	
	 //文件根目录地址
	 public static final String BASEPATH = System.getProperty("catalina.home")
				+ "/webapps/";

	
	//在windows上使用"\\"，在linux上使用"/"
    //public static final String DIR_LOC = "\\";
	  public static final String DIR_LOC = "/";
}
