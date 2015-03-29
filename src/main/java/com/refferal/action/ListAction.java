package com.refferal.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.common.PageList;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.enums.AliCategoryEnum;
import com.refferal.enums.CompanyEnum;
import com.refferal.service.JobDescriptionService;

@Controller
public class ListAction {

	private static final int PAGE_SIZE = 15;
	
	@Autowired
	private JobDescriptionService listService;
	
	@RequestMapping("/list/c{company:\\d+}")
    public ModelAndView list(HttpServletRequest request, @PathVariable int company) {
		
		ModelAndView mv = new ModelAndView("jobs");
		
		String keyword = request.getParameter("keyword");
		int category = ServletRequestUtils.getIntParameter(request, "category", 0);
		String city = request.getParameter("city");
		int offset = ServletRequestUtils.getIntParameter(request, "pager.offset", 0);

		PageList<JobDescriptionDTO> jds = listService.search(keyword, category, city, company, offset ,PAGE_SIZE);
		mv.addObject("jds", jds);
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("keyword", keyword);
		queryParams.put("category", category);
		queryParams.put("city", city);
		queryParams.put("company", company);
		mv.addObject("params", queryParams);
		
		mv.addObject("categorys", AliCategoryEnum.values());
		mv.addObject("companys", CompanyEnum.values());
		
		if (company > 0) {
			mv.addObject("companyName", CompanyEnum.getNameById(company));
		} else {
			mv.addObject("companyName", "高端互联网公司");
		}
		mv.addObject("bannerSelected", "job");
		
        return mv;
    }
	
}
