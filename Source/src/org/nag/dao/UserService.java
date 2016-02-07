package org.nag.dao;

import java.util.List;

//import org.hibernate.Criteria;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//mport org.hibernate.criterion.Example;
import org.nag.model.Group;
import org.nag.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class UserService /*implements EntityService*/{
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User get(int id) {
		return getSessionFactory().getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return (List<User>) getSessionFactory().getCurrentSession().createCriteria(User.class).list();
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void save(User user) {
		getSessionFactory().getCurrentSession().saveOrUpdate(user);
	}
	
	public User create(String firstName, String lastName, String email, Group group) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setGroup(group);
		save(user);
		return user;
	}

	@Transactional(readOnly=false)
	public void edit(int userId, String firstName, String lastName, String email, Group group) {
		User user = getSessionFactory().getCurrentSession().get(User.class, userId);
		if((firstName != null)&&(!firstName.equals(""))) 
			user.setFirstName(firstName);
		if((lastName != null)&&(!lastName.equals(""))) 
			user.setLastName(lastName);
		if((email != null)&&(!email.equals(""))) 
			user.setEmail(email);
		user.setGroup(group);
	}
	
	@Transactional(readOnly=false)
	public void edit(int userId, User newUser) {
		User user = getSessionFactory().getCurrentSession().get(User.class, userId);
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		user.setGroup(newUser.getGroup());
	}

	@Transactional(readOnly=false)
	public void delete(User user) {
		/*Example example = Example.create(user);
		getSessionFactory().getCurrentSession().delete(getSessionFactory().
				getCurrentSession().createCriteria(User.class).add(example).uniqueResult());*/
		getSessionFactory().getCurrentSession().delete(user);
	}
	
	@Transactional(readOnly=false)
	public void delete(int id) {
		getSessionFactory().getCurrentSession().delete(get(id));
	}

}
