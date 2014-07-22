package com.refferal.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Detail {

	@RequestMapping("/detail")
    public ModelAndView main( String[] args ) {
        return new ModelAndView("job_detail");
    }

}
