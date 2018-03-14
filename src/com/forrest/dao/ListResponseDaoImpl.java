package com.forrest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.ListResponseMapper;
import com.forrest.model.ListResponse;

@Repository
public class ListResponseDaoImpl implements ListResponseDao {

	@Autowired
	private ListResponseMapper listResponseMapper;

	@Override
	public void insertListResponse(ListResponse listResponse) {
		listResponseMapper.insertListResponse(listResponse);
	}

}
