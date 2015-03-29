package com.refferal.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.refferal.common.PageList;
import com.refferal.entity.Favorites;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.service.FavoritesService;
import com.refferal.service.JobDescriptionService;

@Controller
public class FavoritesAction {

	private static final int PAGE_SIZE = 15;

	@Autowired
	private FavoritesService favoritesService;
	
	@Autowired
	private JobDescriptionService jobDescriptionService;
	
	@RequestMapping(value={"/favorites"})
    public ModelAndView favorites(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("favorites");
		
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if (userId == null || userId <= 0) {
			return mv;
		}
		
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int startRecord = (pageNo - 1) * PAGE_SIZE;
		
		PageList<Favorites> favorites = favoritesService.search(userId, startRecord, PAGE_SIZE);
		if (favorites.getCount() > 0) {
			for (Favorites favorite : favorites.getList()) {
				JobDescriptionDTO jd = jobDescriptionService.selectById(favorite.getJdId());
				if (jd != null) {
					favorite.setJdName(jd.getName());
					favorite.setCity(jd.getCityId());
					favorite.setDepartment(jd.getDepartment());
					favorite.setStatus(jd.getStatus() > 0);
				}
			}
		}
		
		mv.addObject("favorites", favorites);
		mv.addObject("bannerSelected", "favorites");
		return mv;
	}
	
}
