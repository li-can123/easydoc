package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ProjectAddParam {
    @NotBlank(message = "项目名称不能为空")
    @Length(max = 20, message = "项目名长度必须小于等于20")
    private String name; // 项目名称

    private String description; // 项目描述

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
