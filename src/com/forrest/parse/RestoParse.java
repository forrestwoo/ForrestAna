package com.forrest.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.model.Restaurant;
import com.forrest.utils.HTTPUtils;
import com.forrest.utils.ImageUtils;

public class RestoParse {
	public static List<Restaurant> getData(HttpClient client, String url) throws Exception {
		List<Restaurant> data = new ArrayList<Restaurant>();

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		Elements elements = doc.select("div[class=shop-list J_shop-list shop-all-list]").select("ul").select("li");
		if (elements.size() < 1) {
			return null;
		}
		for (Element ele : elements) {
			String detail = ele.select("div[class=pic]").select("a").attr("href");

			String rid = detail.substring(detail.lastIndexOf("/") + 1);

			String image = ele.select("div[class=pic]").select("a").select("img").attr("src");
			String name = ele.select("div[class=pic]").select("a").select("img").attr("title");
			String commentNum = ele.select("div[class=txt]").select("div[class=comment]").select("span").attr("title");
			String statcont = ele.select("div[class=txt]").select("div[class=comment]").select("a[class=review-num]")
					.select("b").text();
			String address = ele.select("div[class=txt]").select("div[class=tag-addr]").select("span").text();
			String consumptionPerPerson = ele.select("div[class=txt]").select("div[class=comment]")
					.select("a[class=mean-price]").text();

			Restaurant restaurant = new Restaurant();
			restaurant.setName(name);
			restaurant.setCommentNum(commentNum);
			restaurant.setImage(ImageUtils.saveToFile(image));
			restaurant.setStatcont(statcont);
			restaurant.setAddress(address);
			restaurant.setConsumptionPerPerson(consumptionPerPerson);

			restaurant.setTele("010-12345678");
			restaurant.setBusinessHours(" 周一至周日 10:00-14:00 16:00-00:00");
			String city = url.substring(24, url.lastIndexOf("/") - 5);
			// System.out.println("城市 " + city);
			// System.out.println("起始点 " + (url.lastIndexOf("/") + 1));
			// System.out.println("结束点 " + url.lastIndexOf("p"));
			// System.out.println("url " + url);

			String cate = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("p"));
//			System.out.println("类别 " + cate);
			restaurant.setCate(cate);
			restaurant.setCity(city);
			restaurant.setSource("大众点评");
			restaurant.setRid(rid);
			data.add(restaurant);
		}

		return data;
	}
}
