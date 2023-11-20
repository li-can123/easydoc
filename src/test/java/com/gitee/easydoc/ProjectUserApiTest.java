package com.gitee.easydoc;

import org.junit.Test;

import com.gitee.easydoc.api.param.ProjectUserAddParam;
import com.gitee.easydoc.api.param.ProjectUserRemoveParam;

public class ProjectUserApiTest extends BaseTests {

	@Test
	public void add() {
	    ProjectUserAddParam param = new ProjectUserAddParam();
	    param.setProjectId(2L);
		param.setUsername("jim");
		param.setPermissionValue(1L);

		this.postWithJwt("project.user.add", param);
	}
	
	@Test
    public void remove() {
        ProjectUserRemoveParam param = new ProjectUserRemoveParam();
        param.setProjectId(2L);
        param.setUsername("jim");

        this.postWithJwt("project.user.remove", param);
    }
}
