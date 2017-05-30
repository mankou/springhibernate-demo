package mang.demo.springhibernate.dao;

import java.util.List;

import mang.demo.springhibernate.entity.TestUser;


public interface TestDAO {
	public List queryTest();
	
	public Long queryMaxId();
	
	public TestUser saveOrUpdate(TestUser entity);
}
