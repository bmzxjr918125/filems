package com.filems.exception;
/**
 * @ClassName: LlmsServiceException
 * @Description: Service层自定义异常处理类
 * @author BianMingZhou
 * @date 2015-11-26 下午4:16:56
 */
public class BizException extends RuntimeException{
	private static final long serialVersionUID = -8015325449347701255L;

	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
	
}
