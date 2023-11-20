package com.gitee.easydoc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gitee.easydoc.interceptor.LoginInterceptor;
import com.gitee.easyopen.ApiConfig;
import com.gitee.easyopen.interceptor.ApiInterceptor;
import com.gitee.easyopen.support.ApiController;

@Controller
@RequestMapping(value = "/api")
//@CrossOrigin(origins={"*"})
public class IndexController extends ApiController implements EnvironmentAware {

    private String springProfilesPctive;

    @Override
    protected void initApiConfig(ApiConfig apiConfig) {
        apiConfig.setShowDoc(true); // 显示文档页面

        // 配置秘钥键值对
        Map<String, String> appSecretStore = new HashMap<String, String>();
        appSecretStore.put("dociview", "443d4c368de450");
        if ("dev".equals(springProfilesPctive)) {
            appSecretStore.put("test", "123456");
        }

        apiConfig.addAppSecret(appSecretStore);
        apiConfig.setInterceptors(new ApiInterceptor[]{new LoginInterceptor()});
        apiConfig.setJwtExpireIn(3600 * 24 * 30); // jwt过期时间（秒），30天
    }

    @Override
    public void setEnvironment(Environment environment) {
        springProfilesPctive = environment.getProperty("spring.profiles.active");
    }

}
