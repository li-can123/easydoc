package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class ResourceUpdateParam {
    @NotNull(message = "id不能空")
    private Long id;

    @NotBlank(message = "资源名称不能空")
    @Length(min = 1, max = 20, message = "资源名称长度为1~20")
    private String name; // 资源名称

    @NotBlank(message = "资源内容不能空")
    private String content;
    
    @NotNull(message = "parentId不能空")
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
