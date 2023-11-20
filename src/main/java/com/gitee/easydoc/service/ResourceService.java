package com.gitee.easydoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitee.easydoc.entity.Resource;
import com.gitee.easydoc.mapper.ResourceMapper;

@Service
public class ResourceService {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ResourceMapper resourceMapper;
    
    /**
     * 校验文档资源是否可操作
     * @param resId
     */
    public void checkUserRes(long resId) {
        Resource res = resourceMapper.getById(resId);
        projectService.checkProject(res.getProjectId());
    }
    
}
