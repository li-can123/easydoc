package com.gitee.easydoc;

import org.junit.Test;

import com.gitee.easydoc.api.param.IdSearch;
import com.gitee.easydoc.api.param.ResourceCreateParam;
import com.gitee.easydoc.api.param.ResourceFolderCreateParam;
import com.gitee.easydoc.api.param.ResourceSearchParam;
import com.gitee.easydoc.api.param.ResourceUpdateParam;

public class ResourceApiTest extends BaseTests {

    @Test
    public void create() {
        ResourceCreateParam param = new ResourceCreateParam();
        param.setName("查询用户接口");
        param.setProjectId(1L);
        param.setParentId(null);
        param.setContent("aaaaaaaaaaaaaaaaaa");

        this.postWithJwt("resource.create", param);
    }

    @Test
    public void update() {
        ResourceUpdateParam param = new ResourceUpdateParam();
        param.setId(2L);
        param.setName("查询用户接口2");
        param.setContent("ccc");

        this.postWithJwt("resource.update", param);
    }

    @Test
    public void delete() {
        ResourceSearchParam param = new ResourceSearchParam();
        param.setId(419077552393420801L);

        this.postWithJwt("resource.delete", param);
    }
    
    @Test
    public void listProjectRes() {
        IdSearch projectId = new IdSearch();
        projectId.setId(1L);
        
        this.post("project.resource.list", projectId);
    }
    
    @Test
    public void createFolder() {
        ResourceFolderCreateParam param = new ResourceFolderCreateParam();
        param.setName("文档目录");
        param.setProjectId(1L);
        this.postWithJwt("resource.folder.create", param);
    }
}
