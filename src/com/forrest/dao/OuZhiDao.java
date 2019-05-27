package com.forrest.dao;

import org.apache.ibatis.annotations.Param;

import com.forrest.model.OuZhi;

public interface OuZhiDao {
	public void insertOuZhi(OuZhi ouZhi);
	
	public void initOuZhi(@Param("tableName")String tableName,@Param("ouZhi")OuZhi ouZhi);
}
