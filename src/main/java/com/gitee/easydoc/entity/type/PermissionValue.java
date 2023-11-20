package com.gitee.easydoc.entity.type;

import com.gitee.fastmybatis.core.handler.BaseEnum;

public enum PermissionValue implements BaseEnum<Long> {
    READONLY(1), READ_WRITE(2);

    private long val;

    PermissionValue(long val) {
        this.val = val;
    }

    @Override
    public Long getCode() {
        return val;
    }

}
