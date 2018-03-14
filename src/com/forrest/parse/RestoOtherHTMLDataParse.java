package com.forrest.parse;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.model.Restaurant;
import com.forrest.utils.HTTPUtils;

public class RestoOtherHTMLDataParse {
	public static Dictionary<String, List> getData(HttpClient client, String url, String rid) throws Exception {
		Dictionary<String, List> dictionary = new Hashtable<String, List>();

//		List<Review> data = new ArrayList<Review>();
		Restaurant restaurant = new Restaurant();
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		// 电话与营业时间
		Elements elements = doc.select("div[class=basic-info default nug_shop_ab_pv-a]");
		if (elements.size() < 0) {
			return null;
		}
		for (Element ele : elements) {
			String tele = ele.select("p[class=expand-info tel]").select("span[class=item]").text();
			String bh = ele.select("div[class=other J-other Hide]").select("p").select("span[class=item]").text();
			restaurant.setTele(tele);
			restaurant.setBusinessHours(bh);
			restaurant.setRid(rid);
			List<Restaurant> rList = new ArrayList<Restaurant>();
			rList.add(restaurant);
			dictionary.put("restaurant", rList);
		}

		return dictionary;
	}
}
