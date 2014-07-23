package com.refferal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.entity.JobDescription;
import com.refferal.service.ListService;

@Controller
public class ListAction {

	@Autowired
	private ListService listService;
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("jobs");
		
		String keyword = request.getParameter("keyword");
		List<JobDescription> jds = listService.search(keyword);
		System.out.println(keyword);
		System.out.println(jds.size());
		mv.addObject("jds", jds);
		
        return mv;
    }
	
}
