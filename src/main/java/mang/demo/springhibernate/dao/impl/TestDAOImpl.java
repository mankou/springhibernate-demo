package mang.demo.springhibernate.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import mang.demo.springhibernate.dao.TestDAO;
import mang.demo.springhibernate.entity.TestUser;




@Repository
public class TestDAOImpl extends BaseDAOImpl<TestUser> implements TestDAO {

	@Override
	public List queryTest() {
		String hql="from TestUser t ";
		List lis=this.getHibernateTemplate().find(hql);
		return lis;
	}

	@Override
	public Long queryMaxId() {
		String sql="select max(id) as maxid from t_user ";
		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("maxid", LongType.INSTANCE);
		
		List<Long> lis=query.list();
		if(lis!=null && lis.size()>0){
			return lis.get(0);
		}
		
		return null;
	}

	@Override
	public TestUser saveOrUpdate(TestUser entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

}
