package com.forrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.CityInfoDao;
import com.forrest.dao.CookStyleDao;
import com.forrest.dao.RestaurantDao;
import com.forrest.model.Restaurant;
import com.forrest.parse.CityInfoParse;
import com.forrest.parse.RestoParse;
import com.forrest.utils.ForrestUtils;

@Controller
public class RestoController {

	private RestaurantDao restaurantDao;

	@Autowired
	private CityInfoDao cityInfoDao;

	@Autowired
	private CookStyleDao cookStyleDao;

	@Autowired
	public RestoController(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	@RequestMapping("/addRestos")
	public String addRestos() throws Exception {
		HttpClient client = HttpClients.createDefault();
		
		//http://www.dianping.com/beijing/ch10/g251
		String rootUrlString = "http://www.dianping.com/";
//		String rootUrlString = "http://www.dianping.com/beijing/ch10/g251";

		String urlString = "";
		

		List<String> cities = cityInfoDao.selectCities();

		List<String> cates = cookStyleDao.selectCookStyles();
		
		
		for (int i = 0; i < cities.size(); i++) {
//			if (cates.get(i) == "shanghai") {
//				continue;
//			}
		String	temp = rootUrlString + cities.get(i) + "/ch10/";
			
			for (int j = 0; j < cates.size(); j++) {
				urlString=temp + cates.get(j);
				int pages = ForrestUtils.getPageCount(client, urlString);
				if (pages < 1) {
					continue;
				}
				for (int k = 1; k < pages + 1; k++) {
					String urlString1 = urlString + "p" +k;
					List<Restaurant> list = RestoParse.getData(client, urlString1);
					if (list.size() > 0) {
						restaurantDao.insertRestos(list);
					}
				}
			}
		}

		return "addRestos";
	}

	@RequestMapping("/updateRestos")
	public String initData() {

		return "update";
	}
}
