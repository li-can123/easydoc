package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotBlank;

public class ProjectUserRemoveParam {

    @NotNull(message = "projectId不能为null")
    private Long projectId;
    @NotBlank(message = "用户名不能为空")
    private String username;

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

}
