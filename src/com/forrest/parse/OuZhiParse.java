package com.forrest.parse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forrest.utils.ForrestUtils;
import com.forrest.utils.HTTPUtils;

public class OuZhiParse {
	public static List<Float> getData(HttpClient client, String url) throws Exception {
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		String dataString = doc.select("body").text();
		List<Object> list = JSONObject.parseArray(dataString);
		List<Float> odds = new ArrayList<>();
		if (list.size() == 0) {
			for (int i = 0; i < 6; i++) {
				odds.add((float) 0.00);
			}
		} else if (list.size() == 1) {
			JSONArray object1 = (JSONArray) list.get(0);
			odds.add(ForrestUtils.toFloat(object1.get(0)));
			odds.add(ForrestUtils.toFloat(object1.get(1)));
			odds.add(ForrestUtils.toFloat(object1.get(2)));
			odds.add(ForrestUtils.toFloat(object1.get(0)));
			odds.add(ForrestUtils.toFloat(object1.get(1)));
			odds.add(ForrestUtils.toFloat(object1.get(2)));
		} else {
			JSONArray object1 = (JSONArray) list.get(0);
			JSONArray object2 = (JSONArray) list.get(list.size() - 1);

			odds.add(ForrestUtils.toFloat(object1.get(0)));
			odds.add(ForrestUtils.toFloat(object1.get(1)));
			odds.add(ForrestUtils.toFloat(object1.get(2)));
			odds.add(ForrestUtils.toFloat(object2.get(0)));
			odds.add(ForrestUtils.toFloat(object2.get(1)));
			odds.add(ForrestUtils.toFloat(object2.get(2)));
		}

		// list = JSONObject.parseArray(dataString, OuZhi.class);
		// System.out.println("list" + list+list.size());

		return odds;
	}
}
