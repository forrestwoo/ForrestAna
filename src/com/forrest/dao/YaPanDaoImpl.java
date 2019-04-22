package com.forrest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.YaPanMapper;
import com.forrest.model.YaPan;

@Repository
public class YaPanDaoImpl implements YaPanDao {

	@Autowired
	private YaPanMapper yaPanMapper;
	
	@Override
	public void insertYaPan(YaPan yaPan) {
		yaPanMapper.insertYaPan(yaPan);

	}

}
