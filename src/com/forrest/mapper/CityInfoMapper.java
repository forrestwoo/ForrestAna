package com.forrest.mapper;

import java.util.List;

import com.forrest.model.CityInfo;

public interface CityInfoMapper {
public void insertCities(List<CityInfo> list);
public List<String> selectCities();
}
