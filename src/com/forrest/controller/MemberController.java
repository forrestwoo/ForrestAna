package com.forrest.controller;

import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.MemberDao;
import com.forrest.dao.TempTableDao;
import com.forrest.parse.MemberParse;

@Controller
public class MemberController {

	private MemberDao memberDao;
	
	@Autowired
	private TempTableDao tempTableDao;
	
	@Autowired
	public MemberController(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}
	
	@RequestMapping(value = "/addMembers")
	public String addMembers() throws Exception 
	{
		List<String> uidList = tempTableDao.selectUseridFromTemp();
		String urlString = "http://www.dianping.com/member/";
		for (int i = 80; i < uidList.size(); i++) {
			String url = urlString + uidList.get(i);
			System.out.println("ÍøÕ¾µØÖ·£º" + url);
			System.out.println("Ë÷ÒýID£º" + i);
			memberDao.insertMember(MemberParse.getData(HttpClients.createDefault(), url));
		}
		return "addMembers";
	}
}
