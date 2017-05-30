package mang.demo.springhibernate.bo.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mang.demo.springhibernate.bo.TestBO;
import mang.demo.springhibernate.dao.TestDAO;
import mang.demo.springhibernate.entity.TestUser;

@Service("testBO")
@Transactional
public class TestBOImpl implements TestBO {
	
	@Autowired
	private TestDAO testDAO;
	
	public List testQuery() {
		List lis=testDAO.queryTest();
		return lis;
	}

	@Override
	public TestUser testSaveOrUpdate() {
		TestUser testUser=new TestUser();
		Long maxId=testDAO.queryMaxId();
		testUser.setId(maxId+1);
		testUser.setCode("测试code");
		testUser.setName("测试name");
		testDAO.saveOrUpdate(testUser);
		return testUser;
	}

}
