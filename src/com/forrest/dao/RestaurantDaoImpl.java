package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forrest.mapper.RestoMapper;
import com.forrest.model.Restaurant;

@Component
public class RestaurantDaoImpl implements RestaurantDao {
@Autowired
	private RestoMapper restoMapper;
	@Override
	public void insertRestos(List<Restaurant> list) {
		restoMapper.insertRestos(list);
	}

}
