package com.pl.util;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 * prefix = "myconfiguration"：配置文件中哪个下面的所有属性进行一一映射
 * 注意：配置文件的前缀一定不能包含大写字母，不然会报错，即myconfiguration不能够写成myConfiguration
 * 注意：bean必须提供setter方法
 */
@Component
@ConfigurationProperties(prefix="myconfiguration")
public class ConfigurationUtil {
	
	private String sign;
	
	private List<String> strList;
	
	private Map<String,List<String>> mapList;
	
	

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public void setMapList(Map<String, List<String>> mapList) {
		this.mapList = mapList;
	}

	public Map<String, List<String>> getMapList() {
		return mapList;
	}

	@Override
	public String toString() {
		return "ConfigurationUtil [sign=" + sign + ", strList=" + strList + ", mapList=" + mapList + "]";
	}

}
