package com.gitee.easydoc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
  表:doc_template 文档模板
*/
@Table(name = "doc_template")
public class DocTemplate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 模板名称    	
    private String content; // 模板内容    	
    private Long creatorId; // 创建者,为0表示系统模板    	
    private Long projectId; // 属于哪个项目，为0表示共享模板    	
    private Date gmtCreate;
    private Date gmtUpdate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    /** 设置 模板名称,对应字段 doc_template.name */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 模板名称,对应字段 doc_template.name */
    public String getName() {
        return this.name;
    }

    /** 设置 模板内容,对应字段 doc_template.content */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 模板内容,对应字段 doc_template.content */
    public String getContent() {
        return this.content;
    }

    /** 设置 创建者,为0表示系统模板,对应字段 doc_template.creator_id */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /** 获取 创建者,为0表示系统模板,对应字段 doc_template.creator_id */
    public Long getCreatorId() {
        return this.creatorId;
    }

    /** 设置 属于哪个项目，为0表示共享模板,对应字段 doc_template.project_id */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /** 获取 属于哪个项目，为0表示共享模板,对应字段 doc_template.project_id */
    public Long getProjectId() {
        return this.projectId;
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
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DocTemplate other = (DocTemplate) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DocTemplate [");
        sb.append("id=").append(id);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append(", ");
        sb.append("content=").append(content);
        sb.append(", ");
        sb.append("creatorId=").append(creatorId);
        sb.append(", ");
        sb.append("projectId=").append(projectId);
        sb.append(", ");
        sb.append("gmtCreate=").append(gmtCreate);
        sb.append(", ");
        sb.append("gmtUpdate=").append(gmtUpdate);
        sb.append("]");

        return sb.toString();
    }
}
