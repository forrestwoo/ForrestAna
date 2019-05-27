package com.forrest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.forrest.model.BiFa;
import com.forrest.model.Matches;
import com.forrest.model.MatchesGoal;
import com.forrest.model.OuZhi;

public interface MatchesDao {
	public void insertMatches(List<Matches> list);

	public List<Integer> selectMatcheId();

	public void deleteMatches(int mid);

	public void updateMEvent(MatchesGoal matchesGoal);
	
	public void updateBifa(BiFa biFa);
	
	public void initMatches(@Param("tableName")String tableName,@Param("list")List<Matches> list);
	
	public List<Integer> selectMidFromMatches(@Param("tableName1")String tableName1, @Param("tableName2")String tableName2);
	
	public void updateMatches(@Param("tableName1")String tableName1, @Param("tableName2")String tableName2,@Param("mg")MatchesGoal mg);

	public Matches selectFromMatches(@Param("tableName")String tableName,@Param("mid")int mid);
}
