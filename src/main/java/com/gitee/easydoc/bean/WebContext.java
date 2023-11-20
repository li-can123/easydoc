package com.gitee.easydoc.bean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.gitee.easydoc.entity.DocUser;
import com.gitee.easyopen.ApiContext;

public class WebContext {
    private static WebContext INSTANCE = new WebContext();

    private static final String S_USER = "s_user";

    private WebContext() {
    }

    public static WebContext getInstance() {
        return INSTANCE;
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 等同session.setAttribute(key, value);
     * 
     * @param key
     * @param value
     */
    public void setSessionAttr(String key, Object value) {
        this.getSession().setAttribute(key, value);
    }

    /**
     * 等同session.getAttribute(key);
     * 
     * @param key
     * @return
     */
    public Object getSessionAttr(String key) {
        return this.getSession().getAttribute(key);
    }

    public HttpSession getSession() {
        if (getRequest() != null) {
            return getRequest().getSession();
        }
        return null;
    }

    /**
     * 获取当前登录用户
     * 
     * @param session
     * @return
     */
    public DocUser getLoginUser() {
        Map<String, Claim> data = ApiContext.getJwtData();
        if (data == null) {
            return null;
        }
        String userJson = data.get(DocConsts.JWT_KEY_USER).asString();
        if (userJson == null) {
            return null;
        }
        return JSON.parseObject(userJson, DocUser.class);
    }
    
    /**
     * 获取当前登录用户
     * 
     * @param session
     * @return
     */
    public Long getLoginUserId() {

        Map<String, Claim> data = ApiContext.getJwtData();
        if (data == null) {
            return null;
        }
        String id = data.get(DocConsts.JWT_KEY_ID).asString();
        if (id == null) {
            return null;
        }
        return Long.valueOf(id);
    }

    /**
     * 保存当前用户
     * 
     * @param session
     * @param user
     */
    public void setLoginUser(HttpSession session, DocUser user) {
        this.getSession().setAttribute(S_USER, user);
    }

}
