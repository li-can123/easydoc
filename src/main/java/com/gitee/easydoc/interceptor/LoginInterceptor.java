package com.gitee.easydoc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gitee.easydoc.bean.DocConsts;
import com.gitee.easydoc.bean.DocErrors;
import com.gitee.easydoc.bean.WebContext;
import com.gitee.easydoc.entity.DocUser;
import com.gitee.easyopen.ApiMeta;
import com.gitee.easyopen.interceptor.ApiInterceptorAdapter;

/**
 * 登录拦截器，验证用户是否登录
 * @author tanghc
 */
public class LoginInterceptor extends ApiInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object argu)
            throws Exception {
        DocUser user = WebContext.getInstance().getLoginUser();
        if (user == null) {
            throw DocErrors.NO_LOGIN.getException();
        }
        return true;
    }

    @Override
    public boolean match(ApiMeta apiMeta) {
        String name = apiMeta.getName();
        if (name.startsWith(DocConsts.NO_LOGIN_PREFIX)) { // 以‘nologin.’开头的接口不拦截
            return false;
        } else {
            return true;
        }
    }

}
