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

import com.staffing.bean.Employ;

@Component("employDao")
public class EmployDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveEmploy(Employ em) {
		try {
			hibernateTemplate.save(em);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteEmploy(Employ em) {
		try {
			hibernateTemplate.delete(em);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	} 
	public boolean updateEmploy(Employ em) {
		try {
			hibernateTemplate.update(em);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public Employ getEmployById(String employId) {
		try {
			return hibernateTemplate.get(Employ.class, employId);
		} catch(DataAccessException e) {
			return null;
		}
	}
	public List<Employ> getEmploys(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Employ>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<Employ> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Employ";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<Employ>) q.list();
					else
						return (List<Employ>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
