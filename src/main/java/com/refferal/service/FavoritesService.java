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

	public List<Favorites> search(Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return favoritesDao.getFavorites(params);
	}
	
	public int getCount(Integer userId, Integer jdId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("jdId", jdId);
		return favoritesDao.getFavoritesCount(params);
	}

	public int getCount(int userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return favoritesDao.getFavoritesCount(params);
	}

	public void insert(Favorites fav) {
		favoritesDao.insert(fav);
	}

	public void delete(int userId, int jdId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("jdId", jdId);
		favoritesDao.delete(params);
	}
	
}
