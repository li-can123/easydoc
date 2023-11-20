package com.gitee.easydoc;

import org.junit.Test;

import com.gitee.easydoc.api.param.UserRegParam;
import com.gitee.easydoc.bean.util.MD5Util;

public class UserApiTest extends BaseTests {

    @Test
    public void reg() {
        UserRegParam param = new UserRegParam();
        param.setUsername("tim4");
        param.setPassword(MD5Util.encrypt("123456"));
        
        this.post("nologin.user.reg", param);
    }
}
