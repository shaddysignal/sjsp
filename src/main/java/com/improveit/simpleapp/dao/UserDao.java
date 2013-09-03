package com.improveit.simpleapp.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.improveit.simpleapp.model.User;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StandardPasswordEncoder encoder;
	
	private Integer create(User user) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction(); 
		Integer userId = (Integer) s.save(user);
		tx.commit();
		return userId;
	}
	
	private void update(User user) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.update(user);
		tx.commit();
	}
	
	public int updateOrCreate(User user) {
		// Can't find a better place for this
		String passToCrypt = user.getPassword();
		if(passToCrypt != null)
			user.setPassword(encoder.encode(passToCrypt));
		if(getFirst(user.getId()) == null)
			return create(user);
		update(user);
		return user.getId();
	}

	public void remove(User existingUser) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.delete(existingUser);
		tx.commit();
	}
	
	public void setUserDone(int userId) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.createSQLQuery("update improve_it.users as u set u.user_done=1 where u.id=?")
			.setParameter(0, userId).executeUpdate();
		tx.commit();
	}
	
	/**
	 * 
	 * @param params key-paramName and value-paramValue
	 * @return
	 */
	public List<User> getByParams(Map<String, String> params) {
		if(params.isEmpty())
			return getAllUsers();
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		StringBuilder query = new StringBuilder(params.size() * 10);
		query.append("from User U where ");
		for(String param : params.keySet()) {
			query.append("U.").append(param).append(" = '").append(params.get(param)).append("' and ");
		}		
		Query q = s.createQuery(query.substring(0, query.lastIndexOf(" and ")));
		q.setProperties(params);
		List<?> users = q.list();
		tx.commit();
		return (List<User>)users;
		
	}
	
	public List<User> getAllUsers() {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		List<?> users = s.createQuery("from User").list();
		tx.commit();
		return (List<User>)users;
	}
	
	/**
	 * Special for get by id.
	 * 
	 * @param id
	 * @return
	 */
	public User getFirst(int id) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		User user = (User)s.createQuery("from User U where U.id = ?")				
				.setParameter(0, id).uniqueResult();
		tx.commit();
		return user;
	}
	
	/**
	 * Get only first from selected collection. Use for get by unique
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return first by paramName, or null if there is no users with such param
	 */
	public User getFirst(String paramName, String paramValue) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		User user = (User)s.createQuery("from User U where U." + paramName +  " = ?")				
				.setParameter(0, paramValue).uniqueResult();
		tx.commit();
		return user;
	}

}
