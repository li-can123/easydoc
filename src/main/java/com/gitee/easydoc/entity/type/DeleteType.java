package com.gitee.easydoc.entity.type;

public enum DeleteType {
    IS_DELETED(1), NOT_DELETE(0);

    private byte code;

    DeleteType(Integer code) {
        this.code = code.byteValue();
    }

    public byte getCode() {
        return code;
    }

}
