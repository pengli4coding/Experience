package com.pl.entity;
/**
 * 微信接口：https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
 * @Description:调用微信接口返回的openid和session_key实体类
 * @author 910146
 * @date: 2018-11-05 11:54:10
 *
 */
public class OpenidSessionKey {
	private Integer errcode;
	private String errmsg;
	private String session_key;
	private String openid;
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "OpenidSessionKey [errcode=" + errcode + ", errmsg=" + errmsg + ", session_key=" + session_key
				+ ", openid=" + openid + "]";
	}
	
	
}
