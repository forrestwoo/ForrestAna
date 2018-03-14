package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.TempTableMapper;
import com.forrest.model.TempTable;

@Repository
public class TempTableDaoImpl implements TempTableDao {

	@Autowired
	private TempTableMapper tempTableMapper;

	@Override
	public void insertTempData(TempTable tempTable) {
		tempTableMapper.insertTempData(tempTable);

	}

	@Override
	public List<String> selectUseridFromTemp() {
		return tempTableMapper.selectUseridFromTemp();
	}

}
