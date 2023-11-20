package com.gitee.easydoc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  表:doc_user 用户表
*/
@Table(name = "doc_user")
public class DocUser {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	private String username; // 用户名	
	private String password; // 密码	
	private Byte status; // 状态	
	private Date gmtCreate; 	
	private Date gmtUpdate; 	

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	/** 设置 用户名,对应字段 doc_user.username */
	public void setUsername(String username) {
		this.username = username;
	}
	/** 获取 用户名,对应字段 doc_user.username */
	public String getUsername() {
		return this.username;
	}

	/** 设置 密码,对应字段 doc_user.password */
	public void setPassword(String password) {
		this.password = password;
	}
	/** 获取 密码,对应字段 doc_user.password */
	public String getPassword() {
		return this.password;
	}

	/** 设置 状态,对应字段 doc_user.status */
	public void setStatus(Byte status) {
		this.status = status;
	}
	/** 获取 状态,对应字段 doc_user.status */
	public Byte getStatus() {
		return this.status;
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
		DocUser other = (DocUser) obj;
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
        sb.append("DocUser [");
        		        sb.append("id=").append(id);
        		sb.append(", ");        sb.append("username=").append(username);
        		sb.append(", ");        sb.append("password=").append(password);
        		sb.append(", ");        sb.append("status=").append(status);
        		sb.append(", ");        sb.append("gmtCreate=").append(gmtCreate);
        		sb.append(", ");        sb.append("gmtUpdate=").append(gmtUpdate);
                sb.append("]");
		return sb.toString();
	}

}