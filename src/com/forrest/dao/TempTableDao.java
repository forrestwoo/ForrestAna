package com.forrest.dao;

import java.util.List;

import com.forrest.model.TempTable;

public interface TempTableDao {
	public void insertTempData(TempTable tempTable);
	public List<String> selectUseridFromTemp();
}
