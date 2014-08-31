package com.refferal.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.common.PageList;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.enums.AliCategoryEnum;
import com.refferal.service.JobDescriptionService;

@Controller
public class ListAction {

	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private JobDescriptionService listService;
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("jobs");
		String keyword = request.getParameter("keyword");
		int category = ServletRequestUtils.getIntParameter(request, "category", 0);
		String city = request.getParameter("city");

		int offset = ServletRequestUtils.getIntParameter(request, "pager.offset", 0);
		PageList<JobDescriptionDTO> jds = listService.search(keyword, category, city, offset ,PAGE_SIZE);
		mv.addObject("jds", jds);
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("keyword", keyword);
		queryParams.put("category", category);
		queryParams.put("city", city);
		mv.addObject("params", queryParams);
		
		mv.addObject("categorys", AliCategoryEnum.values());
		
        return mv;
    }
	
}
