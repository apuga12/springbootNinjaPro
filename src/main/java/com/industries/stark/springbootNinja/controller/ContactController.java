package com.industries.stark.springbootNinja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.industries.stark.springbootNinja.constant.ViewConstant;
import com.industries.stark.springbootNinja.model.ContactModel;
import com.industries.stark.springbootNinja.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog("ContactController");
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@PreAuthorize("hasRole('ROLE_USER_s')")  // Expresiones Spring, pregunta si el usuario loggeado tiene este rol
	//@PreAuthorize("permitAll()")   // Se puede usar a nivel metodo o clase, incluso servicio
	@GetMapping("/contactform")
	private String redirectContactForm(@RequestParam(name="id", required=true) int id,
			Model model){ 		
		LOG.info(" --- METHOD : /contactform  PARAMS : ");
		ContactModel contactModel = new ContactModel();
		if(id != 0){
			contactModel = contactService.findContactByIdModel(id); 
		}
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel")
	public String cancelForm(){
		LOG.info(" --- METHOD : /cancel ");
		//return ViewConstant.CONTACTS;   // Vista Estatica
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel")ContactModel contactModel,
			Model model){
		LOG.info(" --- METHOD : /addcontact.  PARAMS : "+contactModel.toString());
		
		// Si devolvio algo, implica que inserto en registro en la BD
		if(contactService.addContact(contactModel) != null){
			model.addAttribute("result", 1);
		} else{
			model.addAttribute("result", 0);
		}
		
		//return ViewConstant.CONTACTS;   // Vista Estatica
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts(){
		LOG.info(" --- METHOD : /showcontacts. ");
		ModelAndView mov = new ModelAndView(ViewConstant.CONTACTS);
		
		// Validar que user este autenticado, en caso contario se hace redirect a /logout
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && auth.isAuthenticated()){
			LOG.info(" --- userAuthenticated : "+ auth.getName());
		}
		
		// Obtener el user de session: Obj User de Spring
		User userSession = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mov.addObject("username", userSession.getUsername());
		LOG.info(" --- username / passwd : " +userSession.getUsername() + " / " +userSession.getPassword());
		
		mov.addObject("contacts", contactService.listAllContacts());
		return mov;
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true)int id){
		LOG.info(" --- METHOD : /removecontact ");
		contactService.removeContact(id);
		return showContacts();
	}
	
	
}
