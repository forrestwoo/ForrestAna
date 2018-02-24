package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.RestoMapper;
import com.forrest.model.Restaurant;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {
	@Autowired
	private RestoMapper restoMapper;

	@Override
	public void insertRestos(List<Restaurant> list) {
		restoMapper.insertRestos(list);
	}

	@Override
	public List<String> selectRidFromResto() {
		return restoMapper.selectRidFromResto();
	}

	@Override
	public void updateRestoTele(Restaurant restaurant) {
		restoMapper.updateRestoTele(restaurant);
	}

}
