package com.forrest.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.forrest.mapper.IPMessageMapper;
import com.forrest.model.IPMessage;

@Repository
public class IPMessageDaoImpl implements IPMessageDao {

	private IPMessageMapper ipMessageMapper;

	public IPMessageDaoImpl(IPMessageMapper ipMessageMapper) {
		this.ipMessageMapper = ipMessageMapper;
	}

	@Override
	public void insertIPs(List<IPMessage> ipMessages) {
		ipMessageMapper.insertIPs(ipMessages);
	}

	@Override
	public List<Map<String, Object>> selectproxyip() {
		return ipMessageMapper.selectproxyip();
	}

}
