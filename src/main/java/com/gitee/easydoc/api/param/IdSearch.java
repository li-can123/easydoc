package com.gitee.easydoc.api.param;

import javax.validation.constraints.NotNull;

import com.gitee.fastmybatis.core.query.param.PageParam;

public class IdSearch extends PageParam {

    @NotNull(message = "id不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
