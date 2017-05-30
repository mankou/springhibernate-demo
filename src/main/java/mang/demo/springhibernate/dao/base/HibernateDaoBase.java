package mang.demo.springhibernate.dao.base;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

@SuppressWarnings({"rawtypes","unchecked"})
public class HibernateDaoBase extends HibernateDaoSupport  {
	private Class clazz;
	
	@Autowired
	public void setSessionFactory0(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public HibernateDaoBase() {
//		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
//		clazz = (Class) type.getActualTypeArguments()[0];
	}
	
//	public void saveOrUpdate(T t) {
//		this.getHibernateTemplate().saveOrUpdate(t);
//		
//	}
//
//	public void delete(Serializable... ids) {
//		for (Serializable id : ids) {
//			this.getHibernateTemplate().delete(getById(id));
//		}
//	}
//
//	public T getById(Serializable id) {
//		return (T) this.getHibernateTemplate().get(clazz, id);
//	}

//	public List<T> getAllData(String hql,Object...objects) {
//		return (List<T>) this.getHibernateTemplate().find(hql,objects);
//		
//	}


	/*
	 * 分页查询
	 * (non-Javadoc)
	 * @see com.bdqn.Flower.dao.BaseDao#getDataByPage(java.lang.String, int, int, java.lang.Object[])
	 */
//	public List<T> queryDataByPage(final String hql,final int offset,final int length,final Object...objects){
		
//		List<T> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
//			public Object doInHibernate(Session session) throws HibernateException,
//					SQLException {
//				Query query = session.createQuery(hql);
//				if(objects!=null){
//					for (int i = 0; i < objects.length; i++) {
//						query.setParameter(i, objects[i]);
//					}
//				}
//				query.setFirstResult(offset);
//				query.setMaxResults(length);
//				List<T> list = query.list();
//				return list;
//			}
//		});
//		return list;
//		return null;
//	}

//	public Object queryDataByObject(final String hql, final Object... objects) {
//		Object object = this.hibernateTemplate.execute(new HibernateCallback() {
//			
//			public Object doInHibernate(Session session) throws HibernateException,
//					SQLException {
//				Query query = session.createQuery(hql);
//				for (int i = 0; i < objects.length; i++) {
//					query.setParameter(0, objects[i]);
//				}
//				Object obj= query.uniqueResult();
//				return obj;
//			}
//		});
//		return object;
//		return null;
//	}
}
