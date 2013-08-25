package com.improveit.simpleapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.improveit.simpleapp.intreface.IUserDao;
import com.improveit.simpleapp.model.User;

@Repository
@Transactional
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int updateOrCreate(User user) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		User mergedUser = (User) s.merge(user);
		tx.commit();
		return mergedUser.getId();
	}

	public void remove(User existingUser) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.delete(existingUser);
		tx.commit();
	}
	
	public List<User> getAllUsers() {
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from User").list();
	}
	
	/**
	 * Note: You can get by serial+number, if you need, proceed like
	 * paramName = "serialnumber", paramValue = "dddddddddd"
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return user by one of unique params, null if none match
	 */
	public User getUnique(String paramName, String paramValue) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		StringBuilder queryString = new StringBuilder("from User where ? = ?");
		if(paramName.equals("serialnumber")) {
			queryString.append(" and ? = ?");
			String serial = paramValue.substring(0, 4);
			String number = paramValue.substring(4);
			List<?> users = s.createQuery(queryString.toString())
					.setParameter(0, "user_serial")
					.setParameter(1, serial)
					.setParameter(2, "user_number")
					.setParameter(3, number).list();
			tx.commit();
			return users.size() == 1 ? (User) users.get(0) : null;
		}
		List<?> users = s.createQuery(queryString.toString())
				.setParameter(0, "user_" + paramName)
				.setParameter(1, paramValue).list();
		tx.commit();
		return users.size() == 1 ? (User) users.get(0) : null;
	}

}
