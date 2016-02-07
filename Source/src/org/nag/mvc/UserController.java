package org.nag.mvc;

import javax.validation.Valid;

import org.nag.dao.GroupService;
import org.nag.dao.UserService;
import org.nag.model.Group;
import org.nag.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/nagmvc/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String createUser(@RequestParam(value="groupname", required=false) String groupName,
							 Model model) {
		model.addAttribute(new User());
		model.addAttribute("groups", groupService.getAllGroups());
		model.addAttribute("groupname", groupName);
		return "users/edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editUser(@RequestParam("groupName") String groupName,
						   @RequestParam("user") int userId,
						   Model model){
		model.addAttribute(userService.get(userId));
		model.addAttribute("groups", groupService.getAllGroups());
		model.addAttribute("groupname", groupName);
		return "users/edit";
	}
	
	@RequestMapping(value="/{action}", method=RequestMethod.POST)
	public String addUserFromForm(@Valid User user, BindingResult result,
								  @RequestParam("groupselect") String groupSelected,
								  @PathVariable("action") String action,
								  Model model){
		if(result.hasErrors()) {
			model.addAttribute("groups", groupService.getAllGroups());
			return "users/edit";
		}

		if("NONE".equals(groupSelected)){
			result.rejectValue("group", "no group", "Choose the group name");
			//result.reject("Choose the group name");
			model.addAttribute("groups", groupService.getAllGroups());
			return "users/edit"; 
		}

		Group group = groupService.getByName(groupSelected);
		user.setGroup(group);
		if(action.equals("new")) {
			userService.save(user);
		} else if(action.equals("edit")){
			//userService.edit(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), group);
			userService.edit(user.getId(), user);
		} else 
			return "initpage_page";
		
		return "redirect:/nagmvc/groups?group=" + group.getName();	// equals groupSelected
	}
	
	@RequestMapping("/delete")
	public String deleteUser(@RequestParam("groupName") String groupName, 
							 @RequestParam("user") int userId) {
		userService.delete(userId);
		return "redirect:/nagmvc/groups?group=" + groupName;
	} 

}
