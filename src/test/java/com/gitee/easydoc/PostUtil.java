package com.gitee.easydoc;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.alibaba.fastjson.JSON;

public class PostUtil {
	
	private static final String UTF8 = "UTF-8";
	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final String AUTHORIZATION = "Authorization";
    private static final String PREFIX_BEARER = "Bearer ";
	
	/**
	 * post请求接口
	 * 
	 * @param url
	 *            请求的url
	 * @param params
	 *            参数Map
	 * @param encode
	 *            编码 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, ?> params,String jwt) throws Exception {
		String encode = UTF8;
		 // 使用 POST 方式提交数据
		PostMethod method = new PostMethod(url);
		try {
		    String requestBody = JSON.toJSONString(params);
			method.setRequestEntity(new StringRequestEntity(requestBody, CONTENT_TYPE_JSON, encode));
			if(jwt != null) {
                method.setRequestHeader(AUTHORIZATION, PREFIX_BEARER + jwt);
            }
			HttpClient client = new HttpClient();
			
			int state = client.executeMethod(method); // 返回的状态
			
			if (state != HttpStatus.SC_OK) {
				throw new Exception("HttpStatus is " + state);
			}
			
			String response = method.getResponseBodyAsString();

			response = new String(response.getBytes(encode), encode);
			
			return response; // response就是最后得到的结果
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
	}
	
}
