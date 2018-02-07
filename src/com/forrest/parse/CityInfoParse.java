package com.forrest.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.model.CityInfo;
import com.forrest.utils.HTTPUtils;


public class CityInfoParse {
	public static List<CityInfo> getData(HttpClient client, String url) throws Exception {
		List<CityInfo> data = new ArrayList<CityInfo>();

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		Elements elements = doc.select("li[class=letter-item]").select("div[class=terms]")
				.select("div[class=findHeight]");
		for (Element ele : elements) {
			Elements elements2 = ele.children();
			for (Element e2 : elements2) {
				String cityName = e2.select("a").text();
				String temp = e2.select("a").attr("href");
				String cityth = temp.substring(temp.lastIndexOf("/") + 1);

				
				CityInfo cityInfo = new CityInfo();
				cityInfo.setName(cityName);
				cityInfo.setPinyin(cityth);
				data.add(cityInfo);
			}

		}
		
		return data;
	}
	
	
}
