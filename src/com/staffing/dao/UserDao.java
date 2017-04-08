package com.staffing.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.staffing.bean.User;

@Component("userDao")
public class UserDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteUser(User user) {
		try {
			hibernateTemplate.delete(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateUser(User user) {
		try {
			hibernateTemplate.update(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public User getUserInId(String id) {
		
		try {
			return hibernateTemplate.get(User.class, id);
		} catch(DataAccessException e) {
			return null;
		}
	}
	public List<User> getUsers(final int start, final int lengt) {
		
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<User>>() {
				@SuppressWarnings({ "unchecked"})
				@Override
				public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from User";
					Query q = session.createQuery(hql);
					return (List<User>) q.setFirstResult(start).setMaxResults(lengt).list();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
