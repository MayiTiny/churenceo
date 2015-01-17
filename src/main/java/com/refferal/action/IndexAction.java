package com.refferal.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.cache.IndexJDCache;

/**
 * Hello world!
 *
 */
@Controller
public class IndexAction {
	
	@RequestMapping(value={"/"})
    public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("latests", IndexJDCache.getLatests());
		mv.addObject("recommends", IndexJDCache.getRecommends());
		mv.addObject("bannerSelected", "index");
        return mv;
    }
}
