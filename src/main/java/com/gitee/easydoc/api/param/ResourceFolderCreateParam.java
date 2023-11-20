package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class ResourceFolderCreateParam {
    @NotBlank(message = "资源名称不能空")
    @Length(min = 1, max = 20, message = "资源名称长度为1~20")
    private String name; // 资源名称

    @NotNull(message = "所属项目不能空")
    private Long projectId; // 所属项目

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    
}
