package org.nag.dao;

//import java.util.ArrayList;
import java.util.List;

//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.nag.model.Group;
import org.nag.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
//@Transactional(propagation=Propagation.REQUIRED, readOnly=false, isolation=Isolation.DEFAULT)	//-by default
public class GroupService {
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Group get(int id) {
		return getSessionFactory().getCurrentSession().get(Group.class, id);
	}
	
	public Group getByName(String groupName) {
		return (Group) getSessionFactory().getCurrentSession().createCriteria(Group.class)
								   .add(Restrictions.eq("name", groupName)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups(){
		//return (List<Group>) getSessionFactory().getCurrentSession().createQuery("from Group").list();
		return (List<Group>) getSessionFactory().getCurrentSession().createCriteria(Group.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersForGroup(String groupName) {
		return (List<User>) getSessionFactory().getCurrentSession()
						   .createQuery("from User where group.name = :groupname")
						   .setString("groupname", groupName).list();
	}
	
/*	public void create(Group group) {
		save(group);
	}*/

	//@Transactional(readOnly=false)
	public Group create(String name) {
		Group group = new Group();
		group.setName(name);
		save(group);
		return group;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void save(Group group) {
		getSessionFactory().getCurrentSession().saveOrUpdate(group);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public void edit(int id, String name) {
		Group group = get(id);
		group.setName(name);
	}

	@Transactional(readOnly=false)
	public void delete(Group group) {
		/*Example example = Example.create(group);
		Group groupToDelete = (Group) getSessionFactory().getCurrentSession()
					  		  		  .createCriteria(Group.class).add(example).uniqueResult();
		getSessionFactory().getCurrentSession().delete(groupToDelete);*/
		getSessionFactory().getCurrentSession().delete(group);
	}
}
