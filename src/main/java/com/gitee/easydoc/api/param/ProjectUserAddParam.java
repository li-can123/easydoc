package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class ProjectUserAddParam {

    @NotNull(message = "projectId不能为null")
    private Long projectId;
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20,message = "用户名长度不能超过20")
    private String username;
    @NotNull(message = "权限值不能为空")
    private Long permissionValue;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(Long permissionValue) {
        this.permissionValue = permissionValue;
    }

}
