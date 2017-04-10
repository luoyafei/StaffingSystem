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

import com.staffing.bean.Dashi;

@Component("dashiDao")
public class DashiDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveDashi(Dashi d) {
		try {
			hibernateTemplate.save(d);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteDashi(Dashi d) {
		try {
			hibernateTemplate.delete(d);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateDashi(Dashi d) {
		try {
			hibernateTemplate.update(d);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public Dashi getDashiById(String dashiId) {
		try {
			return hibernateTemplate.get(Dashi.class, dashiId);
		} catch(DataAccessException e) {
			return null;
		}
	}
	public List<Dashi> getDashis(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Dashi>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<Dashi> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Dashi";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<Dashi>) q.list();
					else
						return (List<Dashi>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
