package com.refferal.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.common.PageList;
import com.refferal.entity.JobDescription;
import com.refferal.service.JobDescriptionService;

@Controller
public class ListAction {

	@Autowired
	private JobDescriptionService listService;
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("jobs");
		
		String keyword = request.getParameter("keyword");
		PageList<JobDescription> jds = listService.search(keyword, 0 ,10);
		mv.addObject("jds", jds);
		
        return mv;
    }
	
}
