package com.refferal.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.common.PageList;
import com.refferal.dao.FavoritesDao;
import com.refferal.entity.Favorites;

@Service
public class FavoritesService {

	@Autowired
	private FavoritesDao favoritesDao;

	public PageList<Favorites> search(Integer userId, int startRecord, int pageSize) {
		PageList<Favorites> pageList = new PageList<Favorites>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		int count = favoritesDao.getFavoritesCount(params);
		if (count > 0) {
			params.put("start", startRecord);
			params.put("pageSize", pageSize);
			List<Favorites> favorites = favoritesDao.getFavorites(params);
			pageList.setCount(count);
			pageList.setList(favorites);
		} else {
			pageList.setList(Collections.<Favorites>emptyList());
		}
		return pageList;
	}
	
}