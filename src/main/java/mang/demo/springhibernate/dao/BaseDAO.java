package mang.demo.springhibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
	 public T save(T entity);
	 
	 
	 public T saveOrUpdate(T entity);
	 
	 
	 public List<T> saverOrUpdateList(List<T> lis);
	 
	 /**
	  * 批量保存
	  * */
	 public List<T> saveOrUpdateBatch(List<T> lis);
	 
	 
	 public T delete(T entity);
	 
	 public void deleteList(List<T> list);
	 
	 
	 public T findById(Serializable id);
	 
	 
	 public List<T> findByHQL(String hql, Object... params);
	 
}
