package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.CookStyleMapper;
import com.forrest.model.CookStyle;

@Repository
public class CookStyleDaoImpl implements CookStyleDao {

	@Autowired
	private CookStyleMapper cookStyleMapper;
	
	@Override
	public void insertCookStyles(List<CookStyle> list) {
		cookStyleMapper.insertCookStyles(list);

	}

	@Override
	public List<String> selectCookStyles() {
		// TODO Auto-generated method stub
		return cookStyleMapper.selectCookStyles();
	}

}
