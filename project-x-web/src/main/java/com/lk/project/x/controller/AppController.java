/**
 * Created On : 17 Aug 2017
 *//*

package com.lk.project.x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: Auto-generated Javadoc
*/
/**
 * @author virtualpathum
 * The Class AppController.
 *//*

@Controller
public class AppController {

	*/
/**
	 * Home.
	 *
	 * @param modal the modal
	 * @return the string
	 *//*

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","Student Manager");
		return "index";
	}

	*/
/**
	 * Partial handler.
	 *
	 * @param page the page
	 * @return the string
	 *//*

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

}
*/
