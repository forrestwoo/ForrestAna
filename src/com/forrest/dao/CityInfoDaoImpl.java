package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.CityInfoMapper;
import com.forrest.model.CityInfo;

@Repository
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
