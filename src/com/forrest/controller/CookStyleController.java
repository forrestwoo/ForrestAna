package com.forrest.controller;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.CookStyleDao;
import com.forrest.model.CityInfo;
import com.forrest.model.CookStyle;
import com.forrest.parse.CityInfoParse;
import com.forrest.parse.CookStyleParse;

@Controller
public class CookStyleController {

	private CookStyleDao cookStyleDao;
	
	@Autowired
	public CookStyleController(CookStyleDao cookStyleDao) {
		this.cookStyleDao = cookStyleDao;
	}
	
	@RequestMapping("/addCookStyles")
	public String addCookStyles() throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		String rootUrlString = "http://www.dianping.com/beijing/food";


			List<CookStyle> list = CookStyleParse.getData(client, rootUrlString);
			if (list.size() > 0) {
				cookStyleDao.insertCookStyles(list);;
			}

		return "addCookStyles";
	}
}
