package com.gitee.easydoc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  表:project_user 项目用户
*/
@Table(name = "project_user")
public class ProjectUser {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	private Long projectId; // 项目id	
	private Long userId; // 用户id	
	private Long permissionValue; // 权限值	
	private Date gmtCreate; 	
	private Date gmtUpdate; 	

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	/** 设置 项目id,对应字段 project_user.project_id */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	/** 获取 项目id,对应字段 project_user.project_id */
	public Long getProjectId() {
		return this.projectId;
	}

	/** 设置 用户id,对应字段 project_user.user_id */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/** 获取 用户id,对应字段 project_user.user_id */
	public Long getUserId() {
		return this.userId;
	}

	/** 设置 权限值,对应字段 project_user.permission_value */
	public void setPermissionValue(Long permissionValue) {
		this.permissionValue = permissionValue;
	}
	/** 获取 权限值,对应字段 project_user.permission_value */
	public Long getPermissionValue() {
		return this.permissionValue;
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
		ProjectUser other = (ProjectUser) obj;
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
        sb.append("ProjectUser [");
        		        sb.append("id=").append(id);
        		sb.append(", ");        sb.append("projectId=").append(projectId);
        		sb.append(", ");        sb.append("userId=").append(userId);
        		sb.append(", ");        sb.append("permissionValue=").append(permissionValue);
        		sb.append(", ");        sb.append("gmtCreate=").append(gmtCreate);
        		sb.append(", ");        sb.append("gmtUpdate=").append(gmtUpdate);
                sb.append("]");
		return sb.toString();
	}

}