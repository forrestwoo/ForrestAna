package com.forrest.parse;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.model.Restaurant;
import com.forrest.model.YaPan;
import com.forrest.utils.HTTPUtils;

public class YaPanParse {
	public static YaPan getData(HttpClient client, String url, Integer mid) throws Exception {
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
			String string = elements2.get(1).text();

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
