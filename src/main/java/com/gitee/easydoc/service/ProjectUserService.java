package com.gitee.easydoc.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitee.easydoc.bean.DocErrors;
import com.gitee.easydoc.entity.ProjectUser;
import com.gitee.easydoc.mapper.ProjectUserMapper;
import com.gitee.fastmybatis.core.query.Query;

@Service
public class ProjectUserService {

    @Autowired
    ProjectUserMapper projectUserMapper;

    /**
     * 获取项目对应的权限
     * @param userId
     * @return 返回map，key=projectId，value=permissionValue
     */
    public Map<Long, Long> getUserProjectPermissionValue(long userId) {
        // 用户加入的项目
        List<ProjectUser> userProjectList = projectUserMapper.list(new Query().eq("user_id", userId));
        if (CollectionUtils.isEmpty(userProjectList)) {
            return Collections.emptyMap();
        }
        Map<Long, Long> map = new HashMap<>();
        for (ProjectUser projectUser : userProjectList) {
            map.put(projectUser.getProjectId(), projectUser.getPermissionValue());
        }
        return map;
    }

    /**
     * 用户能否操作项目
     * @param projectId
     * @param userId
     * @return
     */
    public void checkUserProject(long projectId,long userId) {
        boolean couldOper = projectUserMapper.getByQuery(new Query().eq("user_id", userId).eq("project_id", projectId)) != null;
        if(!couldOper) {
            throw DocErrors.ERROR_OPT.getException();
        }
            
    }
    
}
