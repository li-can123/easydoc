package com.gitee.easydoc;

import org.junit.Test;

import com.gitee.easydoc.api.param.ProjectAddParam;
import com.gitee.easydoc.api.param.ProjectUpdateParam;
import com.gitee.fastmybatis.core.query.param.PageParam;

public class ProjectApiTest extends BaseTests {

    @Test
    public void create() {
        ProjectAddParam param = new ProjectAddParam();
        param.setName("文档系统3");
        param.setDescription("记录一些文档信息3");

        this.postWithJwt("project.create", param);
    }

    @Test
    public void list() {
        PageParam param = new PageParam();
        param.setPageIndex(1);
        param.setPageSize(5);

        this.postWithJwt("project.list", param);
    }
    
    @Test
    public void listall() {
        PageParam param = new PageParam();

        this.postWithJwt("project.listall", param);
    }

    @Test
    public void update() {
        ProjectUpdateParam param = new ProjectUpdateParam();
        param.setId(5L);
        param.setName("文档123");
        param.setDescription("123");
        this.postWithJwt("project.update", param);
    }

}
