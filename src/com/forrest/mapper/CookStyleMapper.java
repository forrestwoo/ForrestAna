package com.forrest.mapper;

import java.util.List;

import com.forrest.model.CookStyle;

public interface CookStyleMapper {
	public void insertCookStyles(List<CookStyle> list);
	public List<String> selectCookStyles();
}
