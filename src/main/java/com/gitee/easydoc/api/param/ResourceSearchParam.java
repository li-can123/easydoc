package com.gitee.easydoc.api.param;

public class ResourceSearchParam {
    private Long id;

    private String name; // 资源名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
