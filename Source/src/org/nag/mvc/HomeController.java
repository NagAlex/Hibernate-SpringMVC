package org.nag.mvc;

//import java.io.File;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nag.dao.GroupService;

//import javax.inject.Inject;

import org.nag.dao.UserService;
import org.nag.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller								// Объявить как контроллер (компонент)
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@Value("#{systemEnvironment['CATALINA_HOME']}")
	private String webImagePath;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showInitialPage(Model model){
		List<Group> groups = groupService.getAllGroups();
		model.addAttribute("groups", groups);
/*		Map<Integer, String> imageNames = new HashMap<>();
		for(Group group: groups) {
			File file = new File(webImagePath + "/wtpwebapps/NagHibernateMVC/resources/Images/group" + group.getId() + ".jpg");
			if(file.exists()) imageNames.put(group.getId(), file.getAbsolutePath());
			else imageNames.put(group.getId(), new File(webImagePath + "/wtpwebapps/NagHibernateMVC/resources/Images/group.jpg").getAbsolutePath());
		}
		model.addAttribute("imagenames", imageNames);*/
		return "initpage_page";
	}
	
	
	//@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)		//обрабатывать запросы на получение главной страницы
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		model.put("users", userService.getAll());
		return "home"; 					// Вернуть имя представления
	}
}
