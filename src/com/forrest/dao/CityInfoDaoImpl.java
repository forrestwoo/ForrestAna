package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forrest.mapper.CityInfoMapper;
import com.forrest.model.CityInfo;

@Component
public class CityInfoDaoImpl implements CityInfoDao {

	@Autowired
	private CityInfoMapper cityInfoMapper;

	@Override
	public void insertCities(List<CityInfo> list) {
		cityInfoMapper.insertCities(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forrest.dao.CityInfoDao#selectCities()
	 */
	@Override
	public List<String> selectCities() {

		return cityInfoMapper.selectCities();
	}
}
