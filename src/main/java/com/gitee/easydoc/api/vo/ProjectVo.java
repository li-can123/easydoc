package com.gitee.easydoc.api.vo;

import java.util.Date;
import java.util.List;

public class ProjectVo {
    private Long id;
    private String name; // 项目名称
    private String description; // 项目描述
    private Date gmtCreate;
    private Date gmtUpdate;
    
    private Long permissionValue; // 权限值

    private List<ResourceVo> resList; // 项目下的资源

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public List<ResourceVo> getResList() {
        return resList;
    }

    public void setResList(List<ResourceVo> resList) {
        this.resList = resList;
    }

    public Long getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(Long permissionValue) {
        this.permissionValue = permissionValue;
    }
    
}
