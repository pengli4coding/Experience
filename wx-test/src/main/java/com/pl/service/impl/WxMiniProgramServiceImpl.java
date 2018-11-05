package com.pl.service.impl;

import com.pl.common.constant.ErrorCode;
import com.pl.common.constant.ErrorMsg;
import com.pl.common.exception.CoshipException;
import com.pl.common.util.HttpUtil;
import com.pl.common.util.JsonUtil;
import com.pl.common.util.WxConfigUtil;
import com.pl.entity.OpenidSessionKey;
import com.pl.service.WxMiniProgramService;

public class WxMiniProgramServiceImpl implements WxMiniProgramService {

	@Override
	public OpenidSessionKey getOpenIdAndSessionKey(String code) {
		String url = WxConfigUtil.getString("apis.jscode2session")+"?appid="+WxConfigUtil.getAppId()+"&secret="+WxConfigUtil.getSecret()+"&js_code="+code+"&grant_type=authorization_code";
		String response = HttpUtil.doGetSSL(url);
		if(null == response) {
			throw new CoshipException(ErrorCode.WX_RESPONSE_ERROR_CODE,ErrorMsg.WX_RESPONSE_ERROR_MSG);
		}
		return JsonUtil.jsonToObject(response, OpenidSessionKey.class);
	}

}
