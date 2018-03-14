package com.forrest.mapper;

import java.util.List;

import com.forrest.model.TempTable;

public interface TempTableMapper {
	public void insertTempData(TempTable tempTable);
	public List<String> selectUseridFromTemp();
}
