package com.forrest.controller;

import java.io.IOException;
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
import com.forrest.dao.TempTableDao;
import com.forrest.model.Restaurant;
import com.forrest.model.Review;
import com.forrest.model.TempData;
import com.forrest.model.TempTable;
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
	private CookStyleDao cookStyleDao;

	@Autowired
	private TempTableDao tempTableDao;

	@Autowired
	public RestoController(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	@RequestMapping("/addRestos")
	public String addRestos() throws Exception {

		HttpClient client = HttpClients.createDefault();

		String rootUrlString = "http://www.dianping.com/";

		String urlString = "";

		List<String> cates = cookStyleDao.selectCookStyles();

		String temp = rootUrlString + "beijing/ch10/";

		for (int j = 0; j < cates.size(); j++) {
			urlString = temp + cates.get(j);
			int pages = ForrestUtils.getPageCount(client, urlString, "div[class=page]");
			if (pages < 1) {
				continue;
			}
			for (int k = 1; k < pages + 1; k++) {
				String urlString1 = urlString + "p" + k;
				TempTable tempTable = new TempTable();
				tempTable.setName(urlString1);
				tempTableDao.insertTempData(tempTable);
				List<Restaurant> list = RestoParse.getData(client, urlString1);
				System.out.println("url e......" + urlString1);

				if (list.size() > 0) {
					restaurantDao.insertRestos(list);
				}
			}
		}

		return "addRestos";
	}

	@RequestMapping("getRestoPhotos")
	public String getRestoPhotos() throws Exception, Exception {
		// http://www.dianping.com/shop/10007873/photos
		// List<String> ridList = restaurantDao.selectRidFromResto();
		// String urlString = "http://www.dianping.com/shop/";
		// for (int i = 0; i < ridList.size(); i++) {
		// String string = ridList.get(i);
		// String url = urlString + string + "/photos";
		//
		// System.out.println("ÍøÕ¾µØÖ·£º" + url);
		// System.out.println("Ë÷ÒýID£º" + i);
		//
		// int page = ForrestUtils.getPageCount(HttpClients.createDefault(), url);
		// for (int j = 0; j < page; j++) {
		// String fullurl = url + "?pg=" + j + 1;
		//
		// }
		// }
		return "getRestoPhotos";
	}

	@RequestMapping("/updateRestoTele")
	public String updateRestoTele() throws Exception {
		List<String> ridList = restaurantDao.selectRidFromResto();
		String urlString = "http://www.dianping.com/shop/";
		for (int i = 0; i < ridList.size(); i++) {
			String string = ridList.get(i);
			String url = urlString + string;

			System.out.println("ÍøÕ¾µØÖ·£º" + url);
			System.out.println("Ë÷ÒýID£º" + i);
			
			String tele = RestoParse.getTeleData(HttpClients.createDefault(), url);
			Restaurant restaurant = new Restaurant();
			restaurant.setRid(string);
			if (tele != null) {
				restaurant.setTele(tele);
			}else {
				restaurant.setTele("ÔÝÎÞ");
			}
			restaurantDao.updateRestoTele(restaurant);

		}
		return "addRestos";
	}
}
