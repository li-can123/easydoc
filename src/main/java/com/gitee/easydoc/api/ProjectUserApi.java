package com.gitee.easydoc.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.gitee.easydoc.api.param.ProjectUserAddParam;
import com.gitee.easydoc.api.param.ProjectUserRemoveParam;
import com.gitee.easydoc.bean.DocErrors;
import com.gitee.easydoc.bean.DocException;
import com.gitee.easydoc.bean.WebContext;
import com.gitee.easydoc.entity.DocUser;
import com.gitee.easydoc.entity.Project;
import com.gitee.easydoc.entity.ProjectUser;
import com.gitee.easydoc.mapper.DocUserMapper;
import com.gitee.easydoc.mapper.ProjectMapper;
import com.gitee.easydoc.mapper.ProjectUserMapper;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.fastmybatis.core.query.Query;

/**
 * 项目成员相关接口
 * @author tanghc
 */
@ApiService
public class ProjectUserApi {

    @Autowired
    private ProjectUserMapper projectUserMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private DocUserMapper docUserMapper;

    /**
     * 添加项目用户 
     * 
     * @param user
     * @return
     */
    @Api(name = "project.user.add")
    public void add(ProjectUserAddParam param) {
        Project project = projectMapper.getById(param.getProjectId());
        if (project == null) {
            throw new DocException("项目不存在");
        }
        DocUser user = docUserMapper.getByUsername(param.getUsername());
        if (user == null) {
            throw DocErrors.NO_USER.getException();
        }

        Query query = new Query().eq("project_id", project.getId()).eq("user_id", user.getId());
        ProjectUser rec = projectUserMapper.getByQuery(query);
        if (rec != null) {
            throw new DocException("该用户已添加到此项目");
        }

        ProjectUser projectUser = new ProjectUser();
        projectUser.setProjectId(project.getId());
        projectUser.setUserId(user.getId());
        projectUser.setPermissionValue(param.getPermissionValue());

        projectUserMapper.save(projectUser);
    }

    /**
     * 删除项目用户
     * 
     * @param param
     */
    @Api(name = "project.user.remove")
    public void remove(ProjectUserRemoveParam param) {
        DocUser loginUser = WebContext.getInstance().getLoginUser();
        if(loginUser.getUsername().equals(param.getUsername())) {
            throw new DocException("不能删除自己");
        }
        
        Project project = projectMapper.getById(param.getProjectId());
        if (project == null) {
            throw new DocException("项目不存在");
        }
        DocUser user = docUserMapper.getByUsername(param.getUsername());
        if (user == null) {
            throw new DocException("用户 " + param.getUsername() + " 不存在");
        }

        Query query = new Query().eq("project_id", project.getId()).eq("user_id", user.getId());

        projectUserMapper.deleteByQuery(query);
    }
}
