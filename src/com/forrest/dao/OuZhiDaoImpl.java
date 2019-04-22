package com.forrest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.OuZhiMapper;
import com.forrest.model.OuZhi;

@Repository
public class OuZhiDaoImpl implements OuZhiDao {

	@Autowired
	private OuZhiMapper ouZhiMapper;
	
	@Override
	public void insertOuZhi(OuZhi ouZhi) {
		ouZhiMapper.insertOuZhi(ouZhi);
	}

}
