package com.refferal.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailAction {

	@RequestMapping("/detail")
    public ModelAndView detail( String[] args ) {
        return new ModelAndView("job_detail");
    }

}
