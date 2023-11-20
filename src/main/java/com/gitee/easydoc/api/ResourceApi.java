package com.gitee.easydoc.api;

import com.gitee.easydoc.api.param.IdSearch;
import com.gitee.easydoc.api.param.ResourceCreateParam;
import com.gitee.easydoc.api.param.ResourceFolderCreateParam;
import com.gitee.easydoc.api.param.ResourceSearchParam;
import com.gitee.easydoc.api.param.ResourceUpdateParam;
import com.gitee.easydoc.api.vo.ResourceVo;
import com.gitee.easydoc.bean.WebContext;
import com.gitee.easydoc.bean.util.CopyUtil;
import com.gitee.easydoc.entity.DocUser;
import com.gitee.easydoc.entity.Project;
import com.gitee.easydoc.entity.Resource;
import com.gitee.easydoc.entity.ResourceContent;
import com.gitee.easydoc.entity.type.DeleteType;
import com.gitee.easydoc.mapper.ProjectMapper;
import com.gitee.easydoc.mapper.ResourceContentMapper;
import com.gitee.easydoc.mapper.ResourceMapper;
import com.gitee.easydoc.service.ProjectService;
import com.gitee.easydoc.service.ResourceService;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.query.Sort;
import com.gitee.fastmybatis.core.util.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档相关接口
 * @author tanghc
 */
@ApiService
public class ResourceApi {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceContentMapper resourceContentMapper;

    /**
     * 添加资源
     * 
     * @param param
     */
    @Api(name = "resource.create")
    @Transactional
    public void create(ResourceCreateParam param) {
        long userId = WebContext.getInstance().getLoginUserId();

        Project project = projectMapper.getById(param.getProjectId());
        Assert.notNull(project, "项目不存在");

        Resource resource = new Resource();
        MyBeanUtil.copyProperties(param, resource);

        resource.setProjectId(project.getId());
        resource.setCreatorId(userId);
        resource.setLastUpdatorId(userId);
        resource.setIsDeleted(DeleteType.NOT_DELETE.getCode());

        long parentId = 0L;

        if (param.getParentId() != null) {
            Resource parentProject = resourceMapper.getById(param.getParentId());
            if (parentProject != null) {
                parentId = parentProject.getId();
            }
        }
        resource.setParentId(parentId);

        int i = resourceMapper.save(resource);

        if (i > 0) {
            ResourceContent content = new ResourceContent();
            content.setContent(param.getContent());
            content.setResourceId(resource.getId());
            resourceContentMapper.save(content);
        }
    }

    /**
     * 修改资源
     * 
     * @param param
     */
    @Api(name = "resource.update")
    public void update(ResourceUpdateParam param) {
        resourceService.checkUserRes(param.getId());
        
        Resource resources = resourceMapper.getById(param.getId());
        Assert.notNull(resources, "resource不能为null");

        ResourceContent content = resourceContentMapper.getByColumn("resource_id", resources.getId());

        MyBeanUtil.copyProperties(param, resources);
        content.setContent(param.getContent());

        resourceMapper.update(resources);
        resourceContentMapper.update(content);
    }

    /**
     * 删除资源
     * 
     * @param param
     */
    @Api(name = "resource.delete")
    public void delete(ResourceSearchParam param) {
        resourceService.checkUserRes(param.getId());
        
        Resource res = resourceMapper.getById(param.getId());
        if (res == null) {
            return;
        }
        resourceMapper.delete(res);
    }

    /**
     * 获取项目下的资源
     * 
     * @param param
     * @return
     */
    @Api(name = "project.resource.list")
    public List<ResourceVo> listByProject(IdSearch param) {
        Project project = projectMapper.getById(param.getId());

        Query query = new Query().eq("project_id", project.getId());

        List<Resource> list = resourceMapper.list(query);

        List<ResourceVo> ret = new ArrayList<>(list.size());

        for (Resource resource : list) {
            ResourceVo vo = new ResourceVo();
            MyBeanUtil.copyProperties(resource, vo);

            ResourceContent content = resourceContentMapper.getByColumn("resource_id", resource.getId());
            if (content != null) {
                vo.setContent(content.getContent());
            }

            ret.add(vo);
        }

        return ret;
    }

    /**
     * 创建目录
     * 
     * @param param
     */
    @Api(name = "resource.folder.create")
    public ResourceVo createFolder(ResourceFolderCreateParam param) {
        DocUser loginUser = WebContext.getInstance().getLoginUser();
        Resource res = new Resource();
        res.setCreatorId(loginUser.getId());
        res.setIsDeleted(DeleteType.NOT_DELETE.getCode());
        res.setName(param.getName());
        res.setParentId(0L);
        res.setProjectId(param.getProjectId());
        res.setLastUpdatorId(loginUser.getId());

        resourceMapper.save(res);
        
        return CopyUtil.copyAll(res, ResourceVo.class);
    }

    /**
     * 获取项目下的所有目录
     * 
     * @param projectIdParam
     * @return
     */
    @Api(name = "user.resource.folder.list")
    public List<ResourceVo> listUserFolder(IdSearch projectIdParam) {
        projectService.checkProject(projectIdParam.getId());

        Query query = new Query().eq("project_id", projectIdParam.getId()).eq("parent_id", 0).orderby("gmt_create",
                Sort.ASC);

        List<Resource> list = resourceMapper.list(query);

        return CopyUtil.copyList(list, ResourceVo.class);
    }

    @Api(name = "resource.get")
    public ResourceVo getRes(IdSearch param) {
        resourceService.checkUserRes(param.getId());
        
        ResourceVo vo = new ResourceVo();

        Resource res = resourceMapper.getById(param.getId());
        MyBeanUtil.copyProperties(res, vo);

        ResourceContent content = resourceContentMapper.getByColumn("resource_id", res.getId());
        if (content != null) {
            vo.setContent(content.getContent());
        }

        return vo;
    }
}
