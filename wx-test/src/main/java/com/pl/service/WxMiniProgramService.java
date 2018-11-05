package com.pl.service;

import com.pl.entity.OpenidSessionKey;

public interface WxMiniProgramService {
	/**
	 * 前台小程序调用wx.login()可以获取code，前台再把code传入，后台调用微信接口获取openid和sessionkey
	 * @param code
	 * @return
	 */
	OpenidSessionKey getOpenIdAndSessionKey(String code);
}
