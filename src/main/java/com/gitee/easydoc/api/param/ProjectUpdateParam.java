package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ProjectUpdateParam {

    @NotNull(message = "项目id不能为空")
    private Long id;
    @NotBlank(message = "项目名称不能为空")
    private String name; // 项目名称

    private String description; // 项目描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
