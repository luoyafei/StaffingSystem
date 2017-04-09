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

import com.staffing.bean.Check;

@Component("checkDao")
public class CheckDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveCheck(Check c) {
		try {
			hibernateTemplate.save(c);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteCheck(Check c) {
		try {
			hibernateTemplate.delete(c);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateCheck(Check c) {
		try {
			hibernateTemplate.update(c);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public Check getCheckById(String checkId) {
		try {
			return hibernateTemplate.get(Check.class, checkId);
		} catch(DataAccessException e) {
			return null;
		}
	}
	public List<Check> getChecks(final int start, final int length) {

		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Check>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<Check> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Check";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<Check>) q.list();
					else
						return (List<Check>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
