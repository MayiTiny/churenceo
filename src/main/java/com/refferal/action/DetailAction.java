package com.refferal.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.entity.JobDescription;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.service.JobDescriptionService;

@Controller
public class DetailAction {

	@Autowired
	private JobDescriptionService jobDescriptionService;
	
	@RequestMapping("/detail/{id:\\d+}")
    public ModelAndView detail(@PathVariable(value="id") Integer id) {
		ModelAndView mv = new ModelAndView("job_detail");
		JobDescriptionDTO jd = jobDescriptionService.selectById(id);
		mv.addObject("jd", jd);
        return mv;
    }

}
