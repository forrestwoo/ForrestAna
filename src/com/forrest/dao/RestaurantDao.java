package com.forrest.dao;

import java.util.List;

import com.forrest.model.Restaurant;

public interface RestaurantDao {
	public void insertRestos(List<Restaurant> list);
	public List<String> selectRidFromResto();
	public void updateRestoTele(Restaurant restaurant);
	public void insertResto(Restaurant restaurant);
}
