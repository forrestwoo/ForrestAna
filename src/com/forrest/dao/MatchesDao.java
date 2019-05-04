package com.forrest.dao;

import java.util.List;

import com.forrest.model.Matches;

public interface MatchesDao {
	public void insertMatches(List<Matches> list);
	public List<Integer> selectMatcheId();
	public void deleteMatches(int mid);
}
