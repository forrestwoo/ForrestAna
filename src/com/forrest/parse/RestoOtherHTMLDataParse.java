package com.forrest.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.utils.HTTPUtils;

public class RestoOtherHTMLDataParse {
	public static List<String> getDataFromURL(String url) throws Exception {
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = HTTPUtils.getRawHtml(client, url);
		int statusCode = response.getStatusLine().getStatusCode();
		String entity = "";
		if (statusCode == 200) {
			entity = EntityUtils.toString(response.getEntity(), "utf-8");
			EntityUtils.consume(response.getEntity());
		} else {
			EntityUtils.consume(response.getEntity());
		}
		List<String> data = new ArrayList<String>();

		Document doc = Jsoup.parse(entity);
		Elements elements = doc.select("div[class=basic-info default nug_shop_ab_pv-a]");

		for (Element ele : elements) {
			String tele = ele.select("p[class=expand-info tel]").select("span[class=item]").text();
			String bh = ele.select("div[class=other J-other Hide]").select("p").select("span[class=item]").text();

			data.add(tele);
			data.add(bh);
		}

		return data;
	}

}
