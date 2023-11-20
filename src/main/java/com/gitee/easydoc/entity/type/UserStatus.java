package com.gitee.easydoc.entity.type;

import com.gitee.fastmybatis.core.handler.BaseEnum;

public enum UserStatus implements BaseEnum<Byte> {
	Reg((byte)0),Valid((byte)1),Forbiden((byte)2)
	;

	private byte state;
	
	UserStatus(byte state) {
		this.state = state;
	}
	
	@Override
	public Byte getCode() {
		return state;
	}

}
