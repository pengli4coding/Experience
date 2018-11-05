package com.pl.common.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	// 管理http连接池
	private static PoolingHttpClientConnectionManager connMgr;
	// 请求配置
	private static RequestConfig requestConfig;
	// 超时时间
	private static final int MAX_TIMEOUT = 7000;

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(10);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		configBuilder.setStaleConnectionCheckEnabled(true);

		requestConfig = configBuilder.build();

	}

	/**
	 * 发送SSL GET请求（HTTPS）
	 * 
	 * @param apiUrl
	 * @param json
	 * @return
	 */
	public static String doGetSSL(String apiUrl) {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet = new HttpGet(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return httpStr;
	}

	/**
	 * 发送SSL POST请求（HTTPS）
	 * 
	 * @param apiUrl
	 * @param json
	 * @return
	 */
	public static String doPostSSL(String apiUrl, Object json) {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 创建SSL安全连接
	 *
	 * @return
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {

		try {
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			return sslsf;
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		/*String result = HttpUtil.doGetSSL("https://api.weixin.qq.com/sns/jscode2session?appid=wx51279e956c9b1968&secret=7bf968c94f5ef0293737f03dc5caa3cf&js_code=033iUERi0XdWrn1lq4Si0D9tRi0iUERP&grant_type=authorization_code");
		System.out.println(result);*/
		
		/*String result = HttpUtil.doGetSSL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx51279e956c9b1968&secret=7bf968c94f5ef0293737f03dc5caa3cf");
		System.out.println(result);*/
		/*String r = "{\"touser\": \"oc6S94mVC0NAfkEOg6mE4Mg22hnc\",\"template_id\": \"PLqq2cqPqndoMnTlcefFWlr_m3jtZP3HX9tlpl0zYj4\",\"form_id\": \"1541402748096\",\"data\": {\"keyword1\": {\"value\": \"339208499\"},\"keyword2\": {\"value\": \"2015年01月05日 12:30\"}}}";
		String result = HttpUtil.doPostSSL("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=15_ngL4_vzIwffuVL7T3LV78xxIRAN6R4MX9k5xPbH_jBX9fA1z2yfxJZ7HlYCMYof7EfEDjUqYjo_8NllGS2Ok91g6W31RMdJ_kYcWOrPIer3BWBRFymSJI9oj-00IJKfAHAMNO",r);
		System.out.println(result);*/
		
	}
}
