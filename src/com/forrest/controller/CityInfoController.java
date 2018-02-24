package com.forrest.controller;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.CityInfoDao;
import com.forrest.model.CityInfo;
import com.forrest.model.Restaurant;
import com.forrest.parse.BaiduImageParse;
import com.forrest.parse.CityInfoParse;

@Controller
public class CityInfoController {
	private CityInfoDao cityInfoDao;

	@Autowired
	public CityInfoController(CityInfoDao cityInfoDao) {
		this.cityInfoDao = cityInfoDao;
	}

	@RequestMapping("/addCities")
	public String addCities() throws Exception {
		BaiduImageParse.getImages();


		return "addCities";
	}
}
