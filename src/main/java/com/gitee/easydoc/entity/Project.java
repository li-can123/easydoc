package com.gitee.easydoc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  表:project 项目表
*/
@Table(name = "project")
public class Project {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	private String name; // 项目名称	
	private String description; // 项目描述	
	private Long creatorId; // 创建者userId	
	private Date gmtCreate; 	
	private Date gmtUpdate; 	

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	/** 设置 项目名称,对应字段 project.name */
	public void setName(String name) {
		this.name = name;
	}
	/** 获取 项目名称,对应字段 project.name */
	public String getName() {
		return this.name;
	}

	/** 设置 项目描述,对应字段 project.description */
	public void setDescription(String description) {
		this.description = description;
	}
	/** 获取 项目描述,对应字段 project.description */
	public String getDescription() {
		return this.description;
	}

	/** 设置 创建者userId,对应字段 project.creator_id */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/** 获取 创建者userId,对应字段 project.creator_id */
	public Long getCreatorId() {
		return this.creatorId;
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
		Project other = (Project) obj;
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
        sb.append("Project [");
        		        sb.append("id=").append(id);
        		sb.append(", ");        sb.append("name=").append(name);
        		sb.append(", ");        sb.append("description=").append(description);
        		sb.append(", ");        sb.append("creatorId=").append(creatorId);
        		sb.append(", ");        sb.append("gmtCreate=").append(gmtCreate);
        		sb.append(", ");        sb.append("gmtUpdate=").append(gmtUpdate);
                sb.append("]");
		return sb.toString();
	}

}