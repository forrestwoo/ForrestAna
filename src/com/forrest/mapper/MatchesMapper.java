package com.forrest.mapper;

import java.util.List;

import com.forrest.model.Matches;

public interface MatchesMapper {
public void insertMatches(List<Matches> list);
public List<Integer> selectMatcheId();
public void deleteMatches(int mid );

}
