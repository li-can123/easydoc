package com.gitee.easydoc;

import org.junit.Test;

import com.gitee.easydoc.api.param.IdSearch;

public class TemplateApiTest extends BaseTests {

    @Test
    public void get() {
        IdSearch param = new IdSearch();
        param.setId(1L);
        this.postWithJwt("template.system.get", param);
    }
    
}
