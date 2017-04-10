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

import com.staffing.bean.Salary;

@Component("salaryDao")
public class SalaryDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveSalary(Salary s) {
		try {
			hibernateTemplate.save(s);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteSalary(Salary s) {
		try {
			hibernateTemplate.delete(s);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateSalary(Salary s) {
		try {
			hibernateTemplate.update(s);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public Salary getSalaryById(String salaryId) {
		try {
			return hibernateTemplate.get(Salary.class, salaryId);
		} catch(DataAccessException e) {
			return null;
		}
	}
	public List<Salary> getSalarys(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Salary>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<Salary> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Salary";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<Salary>) q.list();
					else
						return (List<Salary>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
