package com.forrest.controller;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.MatchesDao;
import com.forrest.model.Matches;
import com.forrest.parse.MatchesParse;

@Controller
public class MatchesController {
	
	private MatchesDao matchesDao;
	
	public MatchesController(MatchesDao matchesDao)
	{
		this.matchesDao = matchesDao;
	}
	
	@RequestMapping("/insertMatches")
	public String insertMatches() throws Exception
	{
		HttpClient client = HttpClients.createDefault();
	
		/**
		 String baseString = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=6207&round=";
		String url = "";
		for (int i = 1; i < 39; i++) {
			url = baseString + i;
			List<Matches> list = MatchesParse.getData(client, url);
			
			if (list.size() > 0) {
				matchesDao.insertMatches(list);
			}
		}
		 */
		/*
		 
		 * */
		String baseString = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=13195&round=32";;
		List<Matches> list = MatchesParse.getData(client, baseString);
		
		if (list.size() > 0) {
			matchesDao.insertMatches(list);
		}
		return "insertMatches";
	}

	@RequestMapping("dataManager")
	public String dataManager()
	{
		
		return "dataManager";
	}
	
}
