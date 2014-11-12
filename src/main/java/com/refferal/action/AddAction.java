package com.refferal.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.entity.JobDescription;
import com.refferal.enums.AliCategoryEnum;
import com.refferal.enums.CompanyEnum;
import com.refferal.service.JobDescriptionService;

@Controller
public class AddAction {

	private static final Log LOGGER = LogFactory.getLog(AddAction.class);
	
	@Autowired
	private JobDescriptionService jobDescriptionService;
	
	@RequestMapping(value="/add",method =RequestMethod.GET)
    public ModelAndView add() {
		ModelAndView mv = new ModelAndView("add");
		mv.addObject("functionTypes", AliCategoryEnum.values());
		mv.addObject("companys", CompanyEnum.values());
		return mv;
	}
	
	@RequestMapping(value="/add",method =RequestMethod.POST)
	public void add1(HttpServletRequest request, HttpServletResponse response, JobDescription jd) {
		try {
			if ("ceonb".equals(request.getParameter("password"))) {
				jobDescriptionService.insert(jd);
				response.sendRedirect("detail/" + jd.getId());
			} else {
				response.sendRedirect("");
			}
		} catch (IOException e) {
			LOGGER.error("新增职位后跳转出现异常！", e);
		}
	}
	
}
