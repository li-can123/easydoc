package com.gitee.easydoc.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.gitee.easydoc.api.param.IdSearch;
import com.gitee.easydoc.api.vo.TemplateVo;
import com.gitee.easydoc.bean.util.CopyUtil;
import com.gitee.easydoc.entity.DocTemplate;
import com.gitee.easydoc.mapper.DocTemplateMapper;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.fastmybatis.core.query.Query;

/**
 * 模板接口
 * @author tanghc
 */
@ApiService
public class TemplateApi {

    @Autowired
    DocTemplateMapper docTemplateMapper;
    
    /**
     * 获取系统模板
     * @param search
     * @return
     */
    @Api(name = "template.system.get")
    public TemplateVo getSystemTemplate(IdSearch search) {
        Query query = new Query().eq("id", search.getId()).eq("project_id", 0);
        DocTemplate template = docTemplateMapper.getByQuery(query);
        return CopyUtil.copyAll(template, TemplateVo.class);
    }
    
}
