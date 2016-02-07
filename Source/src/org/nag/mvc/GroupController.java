package org.nag.mvc;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;
import org.apache.commons.io.FileUtils;

import org.nag.dao.GroupService;
import org.nag.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/nagmvc")			//Root path in URL to handle
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Value("#{systemEnvironment['CATALINA_HOME']}/wtpwebapps/NagHibernateMVC/")
	private String webRootPath;
	
	//Processes GET=requests to URL /nagmvc/groups
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String listUsersForGroup (
			@RequestParam("group") String groupName,
								   Model model
	) {
		model.addAttribute("group", groupName);
		model.addAttribute(groupService.getUsersForGroup(groupName));
		return "groups/list";
	}

	@RequestMapping(method=RequestMethod.GET, params="delete")
	public String deleteGroup(@RequestParam("group") String groupName, Model model) {
		groupService.delete(groupService.getByName(groupName));
		model.addAttribute("groups", groupService.getAllGroups());
		return "initpage_page";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String editGroup(@RequestParam("group") String groupName, Model model){
		model.addAttribute(groupService.getByName(groupName));
		return "groups/edit";
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String createGroup(Model model) {
		model.addAttribute(new Group());
		return "groups/edit";
	}
	
	@RequestMapping(value="/{action}", method=RequestMethod.POST)
	public String addGroupFromForm(@Valid Group group, BindingResult bindingResult,
			@RequestParam(value="image", required=false)	//Приём файла (он не обязательно должен присутствовать
			MultipartFile image,
			@PathVariable("action") String actionName ){
		
		if(bindingResult.hasErrors()) {	// Проверка ошибок
			//System.out.println("An error!!! " + bindingResult.getFieldError().getField());
			return "groups/edit";
		}
		if(actionName.equals("new"))
			groupService.save(group);		//Сохранить объект Group
		else if(actionName.equals("edit")){
			groupService.edit(group.getId(), group.getName());	//Редактировать объект Group
		}
		try{
			if(!image.isEmpty()){
				validateImage(image);						//Проверить изображение
				saveImage("group" + group.getId() + ".jpg", image);					//Сохранить файл
			}
		} catch (ImageUploadException e) {
			bindingResult.reject(e.getMessage());
			return "groups/edit";
		}
		return "redirect:/nagmvc/groups?group=" + group.getName();		//Переадресовать после запроса POST
	}
	
	private void validateImage(MultipartFile image) {
		if(!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
	}
	
	private void saveImage(String filename, MultipartFile image) throws ImageUploadException {
		try {
			File file = new File(webRootPath + "/resources/images/" + filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (IOException e) {
			throw new ImageUploadException("Unable to save image", e);
		}
	}
}
