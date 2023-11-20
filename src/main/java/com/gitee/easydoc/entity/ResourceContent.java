package com.gitee.easydoc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  表:resource_content 资源内容表
*/
@Table(name = "resource_content")
public class ResourceContent {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	private Long resourceId; // 资源表ID	
	private String content; // 资源内容	
	private Date gmtCreate; 	
	private Date gmtUpdate; 	

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	/** 设置 资源表ID,对应字段 resource_content.resource_id */
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	/** 获取 资源表ID,对应字段 resource_content.resource_id */
	public Long getResourceId() {
		return this.resourceId;
	}

	/** 设置 资源内容,对应字段 resource_content.content */
	public void setContent(String content) {
		this.content = content;
	}
	/** 获取 资源内容,对应字段 resource_content.content */
	public String getContent() {
		return this.content;
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
		ResourceContent other = (ResourceContent) obj;
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
        sb.append("ResourceContent [");
        		        sb.append("id=").append(id);
        		sb.append(", ");        sb.append("resourceId=").append(resourceId);
        		sb.append(", ");        sb.append("content=").append(content);
        		sb.append(", ");        sb.append("gmtCreate=").append(gmtCreate);
        		sb.append(", ");        sb.append("gmtUpdate=").append(gmtUpdate);
                sb.append("]");
		return sb.toString();
	}

}