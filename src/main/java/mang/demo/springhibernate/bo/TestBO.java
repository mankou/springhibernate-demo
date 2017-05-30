package mang.demo.springhibernate.bo;

import java.util.List;

import mang.demo.springhibernate.entity.TestUser;

public interface TestBO {
	public List testQuery();
	
	
	public TestUser testSaveOrUpdate();
	
}
