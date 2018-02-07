package com.forrest.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.model.CookStyle;
import com.forrest.utils.HTTPUtils;

public class CookStyleParse {
	public static List<CookStyle> getData(HttpClient client, String url) throws Exception {
		List<CookStyle> data = new ArrayList<CookStyle>();

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		Elements elements = doc.select("div[class=nc_item list_cooking]").select("ul[class=nc_list]").select("li");
		System.out.println("±àÂëis " + elements);

		for (Element ele : elements) {
				String name = ele.select("a").text();
				String temp = ele.select("a").attr("href");
				String code = temp.substring(temp.lastIndexOf("/") + 1);
				System.out.println("name is " + name);
				System.out.println("±àÂëis " + code);
				
				CookStyle cookStyle = new CookStyle();
				cookStyle.setName(name);
				cookStyle.setCategoryCode(code);;
				data.add(cookStyle);
		}
		
		return data;
	}
	
}
