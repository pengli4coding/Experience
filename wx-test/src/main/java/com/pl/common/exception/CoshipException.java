package com.pl.common.exception;

/**
 * 业务异常
 * 
 * @Description:
 * @author 910146
 * @date: 2018-11-05 17:45:21
 *
 */
public class CoshipException extends RuntimeException {

	private static final long serialVersionUID = 3440656036107612698L;

	private Integer errorCode;

	public CoshipException(Integer errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	
}
