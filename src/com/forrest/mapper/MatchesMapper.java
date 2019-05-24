package com.forrest.mapper;

import java.util.List;

import com.forrest.model.BiFa;
import com.forrest.model.Matches;
import com.forrest.model.MatchesGoal;

public interface MatchesMapper {
	public void insertMatches(List<Matches> list);

	public List<Integer> selectMatcheId();

	public void deleteMatches(int mid);

	public void updateMEvent(MatchesGoal matchesGoal);
	
	public void updateBifa(BiFa biFa);
}
