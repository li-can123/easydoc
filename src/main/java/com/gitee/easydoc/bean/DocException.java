package com.gitee.easydoc.bean;

import com.gitee.easyopen.exception.ApiException;
import com.gitee.easyopen.message.Error;

public class DocException extends ApiException {
    private static final long serialVersionUID = -7133830382528122271L;

    public DocException(String msg) {
        super(msg);
    }

    public DocException(Error<String> error, Object data) {
        super(error, data);
    }

    public DocException(Error<String> error) {
        super(error);
    }

    public DocException(Exception e) {
        super(e);
    }

    @Override
    public String getCode() {
        return "1001";
    }
}
