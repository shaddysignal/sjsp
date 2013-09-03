package com.improveit.simpleapp.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.improveit.simpleapp.model.User;

/**
 * Use inspector for prevent null to database.
 * With it on user can't update value to null.
 * 
 * @author ashubin
 *
 */
public class UserInterceptor extends EmptyInterceptor {
	
	private static Logger logger = Logger.getLogger(UserInterceptor.class);
	
	@Override
	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		int[] notNull = null;		
		if (entity instanceof User) {
			logger.info("Find Dirty some user.");
			List<Integer> lNotNull = new ArrayList<Integer>(currentState.length);
			for(int i = 0, l = currentState.length; i < l; i++) {
				if(currentState[i] != null)
					lNotNull.add(i);
			}
			notNull = new int[lNotNull.size()];
			for(int i = 0, l = lNotNull.size(); i < l; i++) {
				notNull[i] = lNotNull.get(i);
			}
		}
		return notNull;
	}

}
