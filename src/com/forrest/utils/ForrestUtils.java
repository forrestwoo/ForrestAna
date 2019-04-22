package com.forrest.utils;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForrestUtils {
	public static int getPageCount(HttpClient client, String url, String cssQuery) throws Exception, IOException {
		String htmlString = HTTPUtils.getHTMLData(client, url);
		if (htmlString == null) {
			return 0;
		}
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		/*
		 * div[class=reviews-pages] ÆÀÂÛÒ³Êý page ²ÍÌüÒ³Êý
		 * 
		 */
		Elements elements = doc.select(cssQuery).select("a");
		System.out.println("url " + url);
		if (elements == null || elements.size() == 0) {
			return 1;
		}

		Element element = elements.get(elements.size() - 2);

		return Integer.parseInt(element.text());

	}

	public static float toFloat(Object object) {
		float f = 0;
		if (object instanceof BigDecimal) {
			f = ((BigDecimal) object).floatValue();
		} else if (object instanceof Integer) {
			f = Float.parseFloat(object.toString());
		}
		return f;
	}
}
