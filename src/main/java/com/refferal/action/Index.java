package com.refferal.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello world!
 *
 */
@Controller
public class Index {
	
	@RequestMapping("/index")
    public ModelAndView main( String[] args ) {
        return new ModelAndView("baidu");
    }
}