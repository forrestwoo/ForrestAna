package com.forrest.mapper;

import java.util.List;
import java.util.Map;

import com.forrest.model.IPMessage;

public interface IPMessageMapper {
	public void insertIPs(List<IPMessage> ipMessages);

	public List<Map<String, Object>> selectproxyip();
}
