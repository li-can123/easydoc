package com.gitee.easydoc;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gitee.easydoc.api.param.UserLoginParam;
import com.gitee.easydoc.bean.util.MD5Util;
import com.gitee.easyopen.ApiConfig;
import com.gitee.easyopen.ApiContext;
import com.gitee.easyopen.ParamNames;
import com.gitee.easyopen.verify.DefaultMd5Verifier;

@RunWith(SpringRunner.class)
public class BaseTests {
    private DefaultMd5Verifier signer = new DefaultMd5Verifier();
    {
        ApiConfig apiConfig = new ApiConfig();
        ApiContext.setApiConfig(apiConfig);
    }


    String url = "http://localhost:8081/api";
    String appId = "test";
    String secret = "123456";

    String loginUsername = "admin";
    String loginPassword = "123456";

    public String post(String apiName, Object param) {
        return post(apiName, param, null);
    }

    /**
     * post带上jwt,jwt见BaseTests.java<br>
     * 如果jwt过期则需要运行LoginApiTest重新获取jwt
     *
     * @param apiName
     * @param param
     */
    public String postWithJwt(String apiName, Object param) {
        return post(apiName, param, login());
    }

    private String login() {
        UserLoginParam param = new UserLoginParam();
        param.setUsername(loginUsername);
        param.setPassword(MD5Util.encrypt(loginPassword));

        System.out.println("-------------用户登录-------------");
        String resp = this.post("nologin.sys.login", param);

        JSONObject jsonObj = JSON.parseObject(resp);

        return jsonObj.getString("data");
    }

    public String post(String apiName, Object param, String jwt) {
        try {
            Map<String, String> postData = new HashMap<String, String>();

            postData.put(ParamNames.API_NAME, apiName);
            postData.put(ParamNames.APP_KEY_NAME, appId);
            postData.put(ParamNames.DATA_NAME, URLEncoder.encode(JSON.toJSONString(param), "UTF-8"));
            postData.put(ParamNames.TIMESTAMP_NAME, getTime());
            postData.put(ParamNames.VERSION_NAME, "");
            postData.put(ParamNames.FORMAT_NAME, "json");

            String sign = signer.buildSign(postData, secret);

            postData.put(ParamNames.SIGN_NAME, sign);

            System.out.println("-------------请求内容-------------");
            System.out.println(JSON.toJSONString(postData));
            System.out.println("-------------------------------");

            String resp = PostUtil.post(url, postData, jwt);

            System.out.println("-------------返回结果-------------");
            System.out.println(resp);
            System.out.println("-------------------------------");

            JSONObject json = JSON.parseObject(resp);
            if(!"0".equals(json.getString("code"))) {
                throw new RuntimeException(json.getString("msg"));
            }

            return resp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTime() {
        return new SimpleDateFormat(ParamNames.TIMESTAMP_PATTERN).format(new Date());
    }

    public void print(Object o) {
        System.out.println("=================");
        System.out.println(o);
        System.out.println("=================");
    }

    public void printJson(Object o) {
        System.out.println("=================");
        System.out.println(JSON.toJSONString(o));
        System.out.println("=================");
    }

}
