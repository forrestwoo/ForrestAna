package com.forrest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.ListMapper;
import com.forrest.model.MyList;

@Repository
public class ListDaoImpl implements ListDao{

	@Autowired
	private ListMapper listMapper;
	
	@Override
	public void insertList(MyList list) {
		listMapper.insertList(list);
	}

}
