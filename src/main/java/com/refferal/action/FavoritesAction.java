package com.refferal.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.refferal.entity.Favorites;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.service.FavoritesService;
import com.refferal.service.JobDescriptionService;

@Controller
public class FavoritesAction {

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
		
		List<Favorites> favorites = favoritesService.search(userId);
		if (favorites != null) {
			for (Favorites favorite : favorites) {
				JobDescriptionDTO jd = jobDescriptionService.selectById(favorite.getJdId());
				if (jd != null) {
					favorite.setJdName(jd.getName());
					favorite.setCity(jd.getCityId());
					favorite.setDepartment(jd.getDepartment());
					favorite.setStatus(jd.getStatus() > 0);
				}
			}
		}

		mv.addObject("login", true);
		mv.addObject("favorites", favorites);
		mv.addObject("bannerSelected", "favorites");
		return mv;
	}
	
	@RequestMapping(value={"/favorites/add"})
	@ResponseBody
    public JSONObject add(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		int jdId = ServletRequestUtils.getIntParameter(request, "jdId", -1);
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
		
		if (jdId <= 0 || userId <= 0) {
			json.put("succeed", false);
			json.put("msg", "出错啦T_T 职位有误或未登录。");
			return json;
		}
		
		int totalCount = favoritesService.getCount(userId);
		if (totalCount >= 30) {
			json.put("succeed", false);
			json.put("msg", "最多收藏30个职位，请先取消收藏几个不感兴趣的职位吧~~");
			return json;
		}
		
		int thisCount = favoritesService.getCount(userId, jdId);
		if (thisCount == 0) {
			Favorites fav = new Favorites();
			fav.setUserId(userId);
			fav.setJdId(jdId);
			fav.setCreateTime(new Date());
			fav.setCompanyId(ServletRequestUtils.getIntParameter(request, "companyId", -1));
			favoritesService.insert(fav);
		}
		json.put("succeed", true);
		return json;
		
	}

	@RequestMapping(value={"/favorites/cancel"})
	@ResponseBody
    public JSONObject cancel(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		int jdId = ServletRequestUtils.getIntParameter(request, "jdId", -1);
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
		
		if (jdId <= 0 || userId <= 0) {
			json.put("succeed", false);
			json.put("msg", "出错啦T_T 职位有误或未登录。");
			return json;
		}
		
		favoritesService.delete(userId, jdId);
		json.put("succeed", true);
		return json;
		
	}
	
}
