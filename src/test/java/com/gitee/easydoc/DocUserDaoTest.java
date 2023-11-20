package com.gitee.easydoc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gitee.easydoc.entity.DocUser;
import com.gitee.easydoc.entity.type.UserStatus;
import com.gitee.easydoc.mapper.DocUserMapper;

public class DocUserDaoTest extends BaseTests {

	@Autowired
	private DocUserMapper userMapper;
	
	@Test
	public void testGet() {
		DocUser user = userMapper.getById(1L);
		this.print(user);
	}
	
	
	@Test
    public void getByUsername() {
        DocUser user = userMapper.getByUsername("admin");
        this.print(user);
    }
	
	
	@Test
	public void testInsert() {
		DocUser user = new DocUser();
		user.setUsername("admin");
		user.setPassword("123456");
		user.setStatus(UserStatus.Reg.getCode());
		
		userMapper.save(user);
	}
}
