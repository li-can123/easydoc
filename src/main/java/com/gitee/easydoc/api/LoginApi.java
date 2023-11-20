package com.gitee.easydoc.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.gitee.easydoc.api.param.UserLoginParam;
import com.gitee.easydoc.api.param.UserRegParam;
import com.gitee.easydoc.bean.DocConsts;
import com.gitee.easydoc.bean.DocErrors;
import com.gitee.easydoc.bean.util.MD5Util;
import com.gitee.easydoc.entity.DocUser;
import com.gitee.easydoc.entity.type.UserStatus;
import com.gitee.easydoc.mapper.DocUserMapper;
import com.gitee.easyopen.ApiContext;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.fastmybatis.core.query.Query;

/**
 * 登录注册接口
 * @author tanghc
 */
@ApiService
public class LoginApi {

    @Autowired
    private DocUserMapper docUserMapper;

    /**
     * 用户登录
     * 
     * @param param
     * @return 返回jwt
     */
    @Api(name = "nologin.sys.login")
    public String login(UserLoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        password = MD5Util.encrypt(password);

        Query query = new Query().eq("username", username).eq("password", password);
        DocUser user = docUserMapper.getByQuery(query);

        if (user == null) {
            throw DocErrors.ERROR_USERNAME_PWD.getException();
        } else {
            // 登录成功，生成jwt
            Map<String, String> data = new HashMap<>();
            data.put(DocConsts.JWT_KEY_ID, user.getId().toString());
            data.put(DocConsts.JWT_KEY_USER, JSON.toJSONString(user));

            String jwt = ApiContext.createJwt(data);

            return jwt;
        }
    }

    /**
     * 用户注册
     * 
     * @param param
     */
    @Api(name = "nologin.user.reg")
    public void reg(UserRegParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        password = MD5Util.encrypt(password);
        param.setPassword(password);

        DocUser user = docUserMapper.getByUsername(username);

        if (user != null) {
            throw DocErrors.DUPLICATE_USERNAME.getException();
        } else {
            user = new DocUser();
            user.setStatus(UserStatus.Reg.getCode());
            BeanUtils.copyProperties(param, user);
            docUserMapper.save(user);
        }

    }

}
