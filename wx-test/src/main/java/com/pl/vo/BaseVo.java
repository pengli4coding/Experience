package com.pl.vo;

public class BaseVo {
	private Integer errorCode;
	private String errorMessage;
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "BaseVo [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
