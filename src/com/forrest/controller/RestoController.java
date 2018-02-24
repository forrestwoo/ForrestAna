package com.forrest.controller;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.CityInfoDao;
import com.forrest.dao.CookStyleDao;
import com.forrest.dao.RestaurantDao;
import com.forrest.dao.ReviewDao;
import com.forrest.model.Restaurant;
import com.forrest.model.Review;
import com.forrest.parse.BaiduImageParse;
import com.forrest.parse.CityInfoParse;
import com.forrest.parse.RestoOtherHTMLDataParse;
import com.forrest.parse.RestoParse;
import com.forrest.parse.ReviewParse;
import com.forrest.utils.ForrestUtils;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
public class RestoController {

	private RestaurantDao restaurantDao;

	@Autowired
	private CityInfoDao cityInfoDao;

	@Autowired
	private CookStyleDao cookStyleDao;

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	public RestoController(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	@RequestMapping("/addRestos")
	public String addRestos() throws Exception {

		// HttpClient client = HttpClients.createDefault();
		//
		// String rootUrlString = "http://www.dianping.com/";
		//
		// String urlString = "";
		//
		// List<String> cities = cityInfoDao.selectCities();
		// List<String> cates = cookStyleDao.selectCookStyles();
		//
		//
		// for (int i = 0; i < cities.size(); i++) {
		// String temp = rootUrlString + cities.get(i) + "/ch10/";
		//
		// for (int j = 0; j < cates.size(); j++) {
		// urlString=temp + cates.get(j);
		// int pages = ForrestUtils.getPageCount(client, urlString);
		// if (pages < 1) {
		// continue;
		// }
		// for (int k = 1; k < pages + 1; k++) {
		// String urlString1 = urlString + "p" +k;
		// List<Restaurant> list = RestoParse.getData(client, urlString1);
		// System.out.println("url e......" + urlString1);
		//
		// if (list.size() > 0) {
		// restaurantDao.insertRestos(list);
		// }
		// }
		// }
		// }
		//

		List<String> ridList = restaurantDao.selectRidFromResto();
		String urlString = "http://www.dianping.com/shop/";
		for (int i = 2000; i < ridList.size(); i++) {
			String string = ridList.get(i);
			String url = urlString + string;
			System.out.println("ÍøÕ¾µØÖ·£º" + url);
			System.out.println("Ë÷ÒýID£º" + i);

			Dictionary<String, List> dictionary = ReviewParse.getData(HttpClients.createDefault(), url, string);
			List<Restaurant> rlList = dictionary.get("restaurant");

			if (ridList.size() > 0) {
				restaurantDao.updateRestoTele(rlList.get(0));
			}
			// Restaurant restaurant = new Restaurant();
			// restaurant.setRid(string);
			// restaurant.setTele(RestoParse.getTeleData(HttpClients.createDefault(), url));
			// if (restaurant != null) {
			// restaurantDao.updateRestoTele(restaurant);
			// }
		}
		return "addRestos";
	}

	@RequestMapping("/updateRestos")
	public String initData() {

		return "update";
	}
}
