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
		String url = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=13195&round=26";
		List<Matches> list = MatchesParse.getData(client, url);
		
		if (list.size() > 0) {
			matchesDao.insertMatches(list);
		}
		return "insertMatches";
	}

}
