package com.forrest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.MemberMapper;
import com.forrest.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void insertMember(Member member) {
		memberMapper.insertMember(member);
	}

}
