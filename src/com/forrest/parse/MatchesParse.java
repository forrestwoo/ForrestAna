package com.forrest.parse;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forrest.model.Matches;
import com.forrest.utils.HTTPUtils;

public class MatchesParse {
	public static List<Matches> getData(HttpClient client, String url) throws Exception {
		List<Matches> list = new ArrayList<Matches>();

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		String dataString = doc.select("body").text();
		// System.out.println(dataString);
		list = JSONObject.parseArray(dataString, Matches.class);
		// System.out.println("list" + list+list.size());

		return list;
	}

	public static Dictionary<String,String> getMatchesEventData(HttpClient client, String url) throws Exception {
		Dictionary<String,String> dictionary = new Hashtable<>();
		String zgString = "",kgString = "";
		String lgg="";
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		String dataString = doc.select("body").text();
		JSONObject jsonObject = JSONObject.parseObject(dataString);
		
		JSONArray jsonArray = (JSONArray)jsonObject.get("eventlist");
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = (JSONObject) jsonArray.get(i);
			if (jsonObject.getString("type").equals("goal") 
			|| jsonObject.getString("type").equals("goal-kick") 
			|| jsonObject.getString("type").equals("goal-own")) {
				if (Integer.parseInt(jsonObject.getString("is_home")) == 1) 
				{
					if (zgString.length() < 1) {
						zgString = jsonObject.getString("time");
					}else {
						zgString = zgString + "^" + jsonObject.getString("time");
					}
				
				}
				else {
					if (kgString.length() < 1) {
						kgString = jsonObject.getString("time");
					}else {
						kgString = kgString + "^" + jsonObject.getString("time");
					}
				}
					
			}
			
		}
		
		dictionary.put("zg", zgString);
		dictionary.put("kg", kgString);
		
		return dictionary;
	}
}
