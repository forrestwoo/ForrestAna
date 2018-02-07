package com.forrest.dao;

import java.util.List;

import com.forrest.model.CityInfo;

public interface CityInfoDao {
	public void insertCities(List<CityInfo> list);
	public List<String> selectCities();
		
	
}
