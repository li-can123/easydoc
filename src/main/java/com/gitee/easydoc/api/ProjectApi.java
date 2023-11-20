package com.gitee.easydoc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gitee.easydoc.api.param.ProjectAddParam;
import com.gitee.easydoc.api.param.ProjectUpdateParam;
import com.gitee.easydoc.api.vo.ProjectVo;
import com.gitee.easydoc.bean.DocConsts;
import com.gitee.easydoc.bean.DocErrors;
import com.gitee.easydoc.bean.WebContext;
import com.gitee.easydoc.bean.util.CopyUtil;
import com.gitee.easydoc.entity.DocUser;
import com.gitee.easydoc.entity.Project;
import com.gitee.easydoc.entity.ProjectUser;
import com.gitee.easydoc.mapper.ProjectMapper;
import com.gitee.easydoc.mapper.ProjectUserMapper;
import com.gitee.easydoc.service.ProjectUserService;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.fastmybatis.core.PageInfo;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.query.Sort;
import com.gitee.fastmybatis.core.query.expression.ValueConvert;
import com.gitee.fastmybatis.core.query.param.PageParam;
import com.gitee.fastmybatis.core.util.MapperUtil;
import com.gitee.fastmybatis.core.util.MyBeanUtil;

/**
 * 项目接口
 * @author tanghc
 */
@ApiService
public class ProjectApi {

    @Autowired
    private ProjectMapper projectMapper;
    
    @Autowired
    private ProjectUserMapper projectUserMapper;
    
    @Autowired
    private ProjectUserService projectUserService;

    /**
     * 创建项目
     * 
     * @param project
     * @return
     */
    @Api(name = "project.create")
    public void create(ProjectAddParam param) {
        long userId = WebContext.getInstance().getLoginUserId();
        Project project = new Project();
        BeanUtils.copyProperties(param, project);
        project.setCreatorId(userId);
        projectMapper.save(project);
        
        ProjectUser projectUser = new ProjectUser();
        projectUser.setUserId(userId);
        projectUser.setProjectId(project.getId());
        projectUser.setPermissionValue(DocConsts.PERMISSION_WRITE);
        this.projectUserMapper.save(projectUser);
    }
    
    /**
     * 获取用户项目列表
     * @param param
     * @return
     */
    @Api(name = "project.listall")
    public List<ProjectVo> listAll(PageParam param) {
        DocUser user = WebContext.getInstance().getLoginUser();
        List<ProjectVo> retList = new ArrayList<ProjectVo>();
        
        // 用户加入的项目
        List<ProjectUser> userProjectList = projectUserMapper.list(new Query().eq("user_id", user.getId()).orderby("gmt_create", Sort.ASC));
        for (ProjectUser projectUser : userProjectList) {
            Project project = projectMapper.getById(projectUser.getProjectId());
            ProjectVo vo = CopyUtil.copyAll(project, ProjectVo.class);
            vo.setPermissionValue(projectUser.getPermissionValue());
            
            retList.add(vo);
        }
        
        return retList;
    }
    /**
     * 查询项目
     * 
     * @param param
     * @return
     */
    @Api(name = "project.list")
    public PageInfo<ProjectVo> list(PageParam param) {
        DocUser user = WebContext.getInstance().getLoginUser();
        
        Map<Long, Long> projectPermissionMap = projectUserService.getUserProjectPermissionValue(user.getId());
        
        List<ProjectUser> userProjectList = projectUserMapper.list(new Query().eq("user_id", user.getId()));
        
        Query query = param.toQuery().in("id", userProjectList, new ValueConvert<ProjectUser>() {
            @Override
            public Object convert(ProjectUser obj) {
                return obj.getProjectId();
            }
        }).orderby("gmt_create", Sort.DESC);
        
        PageInfo<Project> pageInfo = MapperUtil.query(projectMapper, query);
        
        return CopyUtil.copyPageInfo(pageInfo, ProjectVo.class, new CopyUtil.CopyFilter<ProjectVo>() {
            @Override
            public void doFilter(Object oldPojo, ProjectVo t) {
                t.setPermissionValue(projectPermissionMap.get(t.getId()));
            }
        });
    }

    /**
     * 修改项目
     * 
     * @param project
     * @return
     */
    @Api(name = "project.update")
    public void update(ProjectUpdateParam param) {
        Long userId = WebContext.getInstance().getLoginUserId();
        projectUserService.checkUserProject(param.getId(), userId);
        Project record = projectMapper.getById(param.getId());
        if (record == null) {
            throw DocErrors.NO_RECORD.getException();
        }
        
        MyBeanUtil.copyProperties(param, record);

        projectMapper.updateIgnoreNull(record);
    }
}
