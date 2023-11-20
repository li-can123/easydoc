package com.gitee.easydoc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gitee.fastmybatis.core.annotation.LogicDelete;

/**
  表:resource 资源表
*/
@Table(name = "resource")
public class Resource {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String name; // 资源名称    
    private Long projectId; // 所属项目 
    private Long creatorId; // 创建者userId    
    private Long lastUpdatorId; // 更改人id    
    private Long parentId; // 父节点ID 
    @LogicDelete
    private Byte isDeleted; // 是否已删除    
    private Date gmtCreate;     
    private Date gmtUpdate;     

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    /** 设置 资源名称,对应字段 resource.name */
    public void setName(String name) {
        this.name = name;
    }
    /** 获取 资源名称,对应字段 resource.name */
    public String getName() {
        return this.name;
    }

    /** 设置 所属项目,对应字段 resource.project_id */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    /** 获取 所属项目,对应字段 resource.project_id */
    public Long getProjectId() {
        return this.projectId;
    }

    /** 设置 创建者userId,对应字段 resource.creator_id */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    /** 获取 创建者userId,对应字段 resource.creator_id */
    public Long getCreatorId() {
        return this.creatorId;
    }

    /** 设置 更改人id,对应字段 resource.last_updator_id */
    public void setLastUpdatorId(Long lastUpdatorId) {
        this.lastUpdatorId = lastUpdatorId;
    }
    /** 获取 更改人id,对应字段 resource.last_updator_id */
    public Long getLastUpdatorId() {
        return this.lastUpdatorId;
    }

    /** 设置 父节点ID,对应字段 resource.parent_id */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /** 获取 父节点ID,对应字段 resource.parent_id */
    public Long getParentId() {
        return this.parentId;
    }

    /** 设置 是否已删除,对应字段 resource.is_deleted */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
    /** 获取 是否已删除,对应字段 resource.is_deleted */
    public Byte getIsDeleted() {
        return this.isDeleted;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
    public Date getGmtUpdate() {
        return this.gmtUpdate;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Resource other = (Resource) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resource [");
                        sb.append("id=").append(id);
                sb.append(", ");        sb.append("name=").append(name);
                sb.append(", ");        sb.append("projectId=").append(projectId);
                sb.append(", ");        sb.append("creatorId=").append(creatorId);
                sb.append(", ");        sb.append("lastUpdatorId=").append(lastUpdatorId);
                sb.append(", ");        sb.append("parentId=").append(parentId);
                sb.append(", ");        sb.append("isDeleted=").append(isDeleted);
                sb.append(", ");        sb.append("gmtCreate=").append(gmtCreate);
                sb.append(", ");        sb.append("gmtUpdate=").append(gmtUpdate);
                sb.append("]");
        return sb.toString();
    }

}