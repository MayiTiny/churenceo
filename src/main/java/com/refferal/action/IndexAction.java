package com.refferal.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello world!
 *
 */
@Controller
public class IndexAction {
	
	@RequestMapping(value={"/"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
