package com.forrest.dao;

import java.util.List;

import com.forrest.model.CookStyle;

public interface CookStyleDao {
public void insertCookStyles(List<CookStyle> list);
public List<String> selectCookStyles();
}
