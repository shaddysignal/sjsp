package com.improveit.simpleapp.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.improveit.simpleapp.model.User;

@Repository
@Transactional
public class UserDao {

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
	
	public void setUserDone(int userId) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.createSQLQuery("insert into improve_it.usersDone (?) values (?)")
			.setParameter(0, "user_id").setParameter(1, userId).executeUpdate();
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
		StringBuilder query = new StringBuilder("from User U where ");
		query.setLength(params.size() * 10);
		for(String param : params.keySet()) {
			query.append("U.").append(param).append(" = :").append(param).append(" and ");
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
	 * Get only first from selected collection. Use for get by unique
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return first from collection, or null if there is no users with such param
	 */
	public User getFirst(String paramName, String paramValue) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		User user = (User)s.createQuery("from User U where U." + paramName +  " = ?")				
				.setParameter(1, paramValue).uniqueResult();
		tx.commit();
		return user;
	}

}
