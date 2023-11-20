package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class ResourceCreateParam {
    @NotBlank(message = "资源名称不能空")
    @Length(min = 1, max = 20, message = "资源名称长度为1~20")
    private String name; // 资源名称

    @NotNull(message = "所属项目不能空")
    private Long projectId; // 所属项目

    private Long parentId;

    @NotBlank(message = "资源内容不能空")
    private String content;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
