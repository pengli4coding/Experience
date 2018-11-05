package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pl.common.util.WxConfigUtil;
import com.pl.service.WxMiniProgramService;


/**
 * 微信小程序controller
 * 
 * @Description:
 * @author 910146
 * @date: 2018-11-05 13:36:34
 *
 */
@RestController
public class WxMiniProgramController {

	/*private static final String JSCODE2SESSION_API = "https://api.weixin.qq.com/sns/jscode2session";
	private static final String APPID = "wx51279e956c9b1968";
	private static final String SECRET = "7bf968c94f5ef0293737f03dc5caa3cf";
	private static final String GRANT_TYPE = "authorization_code";*/
	
	@Autowired
	private WxMiniProgramService wxMiniProgramService;

	@RequestMapping(path = "/sendTemplateMessage", method = RequestMethod.GET)
	public String sendTemplateMessage() {
		String appid = WxConfigUtil.getAppId();
		return appid;
	}
}
