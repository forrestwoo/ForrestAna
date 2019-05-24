package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.MatchesMapper;
import com.forrest.model.BiFa;
import com.forrest.model.Matches;
import com.forrest.model.MatchesGoal;

@Repository
public class MatchesDaoImpl implements MatchesDao {

	@Autowired
	private MatchesMapper matchesMapper;

	@Override
	public void insertMatches(List<Matches> list) {
		matchesMapper.insertMatches(list);

	}

	@Override
	public List<Integer> selectMatcheId() {
		List<Integer> list = matchesMapper.selectMatcheId();
		return list;
	}

	@Override
	public void deleteMatches(int mid) {
		matchesMapper.deleteMatches(mid);
	}

	@Override
	public void updateMEvent(MatchesGoal matchesGoal){
		matchesMapper.updateMEvent(matchesGoal);
	}

	@Override
	public void updateBifa(BiFa biFa) {
		matchesMapper.updateBifa(biFa);
	}
}
