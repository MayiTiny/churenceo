package com.refferal.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class List {

	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
        return new ModelAndView("jobs");
    }
	
}
