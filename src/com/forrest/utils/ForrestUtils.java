package com.forrest.utils;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForrestUtils {
	public static int getPageCount(HttpClient client, String url) throws Exception, IOException {
		String htmlString = HTTPUtils.getHTMLData(client, url);
		if (htmlString == null) {
			return 0;
		}
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		Elements elements = doc.select("div[class=page]").select("a");
		System.out.println("url " + url);
		if (elements.size() == 0) {
			return 0;
		}
		if (elements.size() == 1) {
			return 1;
		}
		Element element = elements.get(elements.size() - 2);

		return Integer.parseInt(element.text());

	}
}
