package com.gitee.easydoc;

import org.junit.Test;

import com.gitee.easydoc.api.param.UserLoginParam;
import com.gitee.easydoc.bean.util.MD5Util;

public class LoginApiTest extends BaseTests {
    
    @Test
    public void login() {
        UserLoginParam param = new UserLoginParam();
        param.setUsername("admin");
        param.setPassword(MD5Util.encrypt("123456"));
        
        this.post("nologin.sys.login",param);
    }
	
}
