package com.gitee.easydoc.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.gitee.easydoc.entity.DocUser;

import com.gitee.fastmybatis.core.mapper.CrudMapper;


public interface DocUserMapper extends CrudMapper<DocUser, Long> {
    @Select("select * from doc_user t where t.username=#{username} limit 1")
    @ResultMap(BASE_RESULT_MAP)
    DocUser getByUsername(@Param("username") String username);
}
