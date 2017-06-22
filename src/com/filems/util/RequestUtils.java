package com.filems.util;
import org.apache.struts2.ServletActionContext;
/**
 * <p>ClassName: RequestUtils</p>
 * <p>@Description: 封装Struts2 request请求</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:08:38</p>
 */
public class RequestUtils {
	public static String getStrParamter(String name){
		String str =ServletActionContext.getRequest().getParameter(name);
		
		return str;
	}

	public static int getIntParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if (str == null || str.trim().equals("")) {
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static boolean getBooleanParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if ("true".equals(str) || "1".equals(str) || "on".equals(str)) {
			return true;
		}
		return false;
	}

	public static float getFloatParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if (str == null || str.trim().equals("")) {
			return 0;
		}

		return Float.parseFloat(str);
	}
	public static double getDoubleParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if (str == null || str.trim().equals("")) {
			return 0;
		}
		
		return Double.parseDouble(str);
	}

}
