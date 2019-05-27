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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forrest.model.BiFa;
import com.forrest.model.Matches;
import com.forrest.model.YaPan;
import com.forrest.utils.ForrestUtils;
import com.forrest.utils.HTTPUtils;

public class MatchesParse {
	public static List<Matches> getData(HttpClient client, String url) throws Exception {
		List<Matches> list = new ArrayList<Matches>();

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		String dataString = doc.select("body").text();
		list = JSONObject.parseArray(dataString, Matches.class);

		return list;
	}

	public static Dictionary<String, String> getMatchesEventData(HttpClient client, String url) throws Exception {
		Dictionary<String, String> dictionary = new Hashtable<>();
		String zgString = "", kgString = "";
		String lgg = "";
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		String dataString = doc.select("body").text();
		JSONObject jsonObject = JSONObject.parseObject(dataString);

		JSONArray jsonArray = (JSONArray) jsonObject.get("eventlist");
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = (JSONObject) jsonArray.get(i);
			if (jsonObject.getString("type").equals("goal") || jsonObject.getString("type").equals("goal-kick")
					|| jsonObject.getString("type").equals("goal-own")) {
				if (Integer.parseInt(jsonObject.getString("is_home")) == 1) {
					if (zgString.length() < 1) {
						zgString = jsonObject.getString("time");
					} else {
						zgString = zgString + "^" + jsonObject.getString("time");
					}

				} else {
					if (kgString.length() < 1) {
						kgString = jsonObject.getString("time");
					} else {
						kgString = kgString + "^" + jsonObject.getString("time");
					}
				}

			}

		}

		dictionary.put("zg", zgString);
		dictionary.put("kg", kgString);

		return dictionary;
	}
	
	public static List<Float> getBiFaZhiData(HttpClient client, String url) throws Exception {
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

		return odds;
	}
	
	public static List<Integer> getBiFaMoneyData(HttpClient client, String url) throws Exception {
		List<Integer> ms = new ArrayList<>();
		Document document = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		Elements elements = document.select("table[class=pub_table pl_table_data  bif-yab]").select("tbody").select("tr");

		if (elements.size()<1) {
			return null;
		}
		
		for (int i = 2; i < 5; i++) {
			Element element= elements.get(i);
			Elements e2 = element.select("td");
			Element ee = e2.get(7);
			if (ee.text().equals("-")) {
				 ms.add(0);
				 continue;
			}
			String ii = ee.text().replaceAll(",","");
			
			ms.add(Integer.valueOf(ii));
		}

		return ms;
	}
	
	public static YaPan getYaPanData(HttpClient client, String url, Integer mid) throws Exception {
		Document document = Jsoup.parse(HTTPUtils.getHTMLData(client, url));

		Elements elements = document.select("tr[id=293]");
		Elements elements3 = document.select("tr[id=6]");
		if (elements3.size() < 1 && elements.size() < 1) {
			return null;
		}
		YaPan yaPan = new YaPan();
		yaPan.setMid(mid);
		if (elements.size() > 0) {
			Element element = elements.get(0);
			Elements elements2 = element.select("tbody").select("td[row=1]");
			yaPan.setMid(mid);
			yaPan.setA1(elements2.get(0).text());
			yaPan.setA2(elements2.get(1).text());

			yaPan.setA3(elements2.get(2).text());
			yaPan.setAa1(elements2.get(3).text());
			yaPan.setAa2(elements2.get(4).text());
			yaPan.setAa3(elements2.get(5).text());
		}
		if (elements3.size() > 0) {
			Element element11 = elements3.get(0);
			Elements elements4 = element11.select("tbody").select("td[row=1]");
			yaPan.setMid(mid);
			yaPan.setB1(elements4.get(0).text());
			yaPan.setB2(elements4.get(1).text());
			yaPan.setB3(elements4.get(2).text());
			yaPan.setBb1(elements4.get(3).text());
			yaPan.setBb2(elements4.get(4).text());
			yaPan.setBb3(elements4.get(5).text());
		}

		return yaPan;
	}
	
	public static YaPan getDaXiaoData(HttpClient client, String url, Integer mid) throws Exception {
		Document document = Jsoup.parse(HTTPUtils.getHTMLData(client, url));

		Elements elements = document.select("tr[id=293]");
		Elements elements3 = document.select("tr[id=6]");
		if (elements3.size() < 1 && elements.size() < 1) {
			return null;
		}
		YaPan yaPan = new YaPan();
		yaPan.setMid(mid);
		if (elements.size() > 0) {
			Element element = elements.get(0);
			Elements elements2 = element.select("tbody").select("td[row=1]");
			yaPan.setMid(mid);
			yaPan.setA1(elements2.get(0).text());
			yaPan.setA2(elements2.get(1).text());

			yaPan.setA3(elements2.get(2).text());
			yaPan.setAa1(elements2.get(3).text());
			yaPan.setAa2(elements2.get(4).text());
			yaPan.setAa3(elements2.get(5).text());
		}
		if (elements3.size() > 0) {
			Element element11 = elements3.get(0);
			Elements elements4 = element11.select("tbody").select("td[row=1]");
			yaPan.setMid(mid);
			yaPan.setB1(elements4.get(0).text());
			yaPan.setB2(elements4.get(1).text());
			yaPan.setB3(elements4.get(2).text());
			yaPan.setBb1(elements4.get(3).text());
			yaPan.setBb2(elements4.get(4).text());
			yaPan.setBb3(elements4.get(5).text());
		}

		return yaPan;
	}
}
