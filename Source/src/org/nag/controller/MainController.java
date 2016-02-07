package org.nag.controller;

//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
import org.nag.dao.GroupService;
import org.nag.dao.UserService;
import org.nag.model.Group;
import org.nag.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainController {

	public static void main(String[] args) {

/*		//We don't need that any more since we define all configurations in spring configuration file (applicationContext.xml)
 		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		UserService userService = new UserService(sessionFactory);
		GroupService groupService = new GroupService(sessionFactory);
*/
		ApplicationContext context = new ClassPathXmlApplicationContext("nag-servlet.xml");
		UserService userService = context.getBean("userService", UserService.class);
		GroupService groupService = context.getBean("groupService", GroupService.class);
		
/*		Group group1 = groupService.create("FiC");
		Group group2 = groupService.create("Other");

		User user1 = userService.create("Alexander", "Nekora", "Alexander.nag81@gmail.com", group1);
		User user2 = userService.create("Nina", "Korinna", "nina.korinna@fcbank.com.ua", group1);
		User user3 = userService.create("Valentina", "Yeremenko", "valentyna.yeremenko@fcbank.com.ua", group1);
		User user4 = userService.create("Serhiy", "Tarasenko", "serhii.tarasenko@fcbank.com.ua", group2);
		User user5 = userService.create("Tolik", "Rudenko", "Ramailbox@ukr.net", group2);
*/		
		groupService.edit(1, "FiC updated");
		
		//userService.delete(user4);

		Group group = groupService.get(1);
		System.out.println("Users of group: " + group.getName());
		for(User user: group.getUsers()) {
			System.out.println("User " + user.getId() + ": " + user.getFirstName() + " " +
							   user.getLastName() + " " + user.getEmail());
		}
		System.out.println();
		
//		userService.edit(1, "Alexander", "Nekora", null);

		//User userEd = userService.get(1);
/*		System.out.println("User " + userEd.getId() + ": " + userEd.getFirstName() + " " +
				   userEd.getLastName() + " " + userEd.getEmail());
		System.out.println("User's group: " + userEd.getGroup().getName());
*/		
		for(User user: userService.getAll()) 
			System.out.println("User " + user.getId() + ": " + user.getFirstName() + " " +
					   user.getLastName() + " " + user.getEmail()+ "  group: " + user.getGroup().getName());

		((ConfigurableApplicationContext) context).close(); ;
		//System.exit(0);
		

	}

}
