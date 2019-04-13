package com.forrest.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forrest.model.Matches;
import com.forrest.utils.HTTPUtils;


public class MatchesParse {
	public static List<Matches> getData(HttpClient client, String url) throws Exception {
		List<Matches> list = new ArrayList<Matches>();

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		String dataString = doc.select("body").text();
//		System.out.println(dataString);
		list = JSONObject.parseArray(dataString, Matches.class);
//		System.out.println("list" + list+list.size());
for (Matches matches : list) {
	System.out.println(matches.getHstanding());
}
		return list;
	}

}
