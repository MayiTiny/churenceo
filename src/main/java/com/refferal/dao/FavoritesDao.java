package com.refferal.dao;

import java.util.List;
import java.util.Map;

import com.refferal.entity.Favorites;

public interface FavoritesDao {

	List<Favorites> getFavorites(Map<String, Object> params);

	int insert(Favorites fav);
	
	int getFavoritesCount(Map<String, Object> params);

	void delete(Map<String, Object> params);
	
}
