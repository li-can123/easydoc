package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotBlank;

public class UserLoginParam {
    @NotBlank(message = "用户名不能空")
    private String username; // 用户名
    @NotBlank(message = "密码不能空")
    private String password; // 密码

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
