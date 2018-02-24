package com.forrest.mapper;

import java.util.List;

import com.forrest.model.Restaurant;

public interface RestoMapper {
	public void insertRestos(List<Restaurant> list);
	public Restaurant selectRestoByID(long id);
	public void addField(List<String> idList);
	public List<Long> selectRestoId();
	public List<String> selectRidFromResto();
	public void updateRestoTele(Restaurant restaurant);
}
