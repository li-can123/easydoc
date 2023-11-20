package com.gitee.easydoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitee.easydoc.bean.DocErrors;
import com.gitee.easydoc.bean.WebContext;
import com.gitee.easydoc.mapper.ProjectUserMapper;
import com.gitee.fastmybatis.core.query.Query;

@Service
public class ProjectService {

    @Autowired
    private ProjectUserMapper projectUserMapper;
    
    /**
     * 是否是用户的项目
     * @param projectId
     * @return
     */
    public boolean isUserProject(long projectId) {
        Long userId = WebContext.getInstance().getLoginUserId();
        long count = projectUserMapper.getCount(new Query().eq("user_id", userId).eq("project_id", projectId));
        return count > 0;
    }
    
    public void checkProject(long projectId) {
        if(!this.isUserProject(projectId)) {
            throw DocErrors.ERROR_OPT.getException();
        }
    }
    
}
