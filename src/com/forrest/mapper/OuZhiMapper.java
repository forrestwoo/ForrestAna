package com.forrest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forrest.model.Matches;
import com.forrest.model.OuZhi;

public interface OuZhiMapper {
	public void insertOuZhi(OuZhi ouZhi);
	public void initOuZhi(@Param("tableName")String tableName,@Param("ouZhi")OuZhi ouZhi);
}
