package com.forrest.dao;

import java.util.List;
import java.util.Map;

import com.forrest.model.IPMessage;

public interface IPMessageDao {
	public  void insertIPs(List<IPMessage> ipMessages);
	public  List<Map<String, Object>> selectproxyip();
}
