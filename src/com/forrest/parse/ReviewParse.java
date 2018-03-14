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

import com.forrest.model.Restaurant;
import com.forrest.model.Review;
import com.forrest.utils.HTTPUtils;
import com.forrest.utils.ImageType;
import com.forrest.utils.ImageUtils;

public class ReviewParse {
	public static List<Review> getData(HttpClient client, String url, String rid) throws Exception {

		List<Review> data = new ArrayList<Review>();
		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));

		Elements rElements = doc.select("div[class=reviews-items]").select("ul").select("li[class!=item]");

		if (rElements.size() < 1) {
			return null;
		}
		for (Element ele : rElements) {

			String user = ele.select("div[class=main-review]").select("div[class=dper-info]").select("a").text();
			int userid = Integer.parseInt(ele.select("a[class=dper-photo-aside]").attr("data-user-id"));
			String avatar = ele.select("a[class=dper-photo-aside]").select("img").attr("data-lazyload");
			String content = ele.select("div[class=main-review]").select("div[class=review-words Hide]")
					.select("div[class!=less-words]").text();
			String tmp = " ÊÕÆðÆÀÂÛ";

			if (content.length() > 1 && content.indexOf(tmp) > 1) {
				content = content.substring(0, content.indexOf(tmp));
			}

			Elements e = ele.select("div[class=main-review]").select("div[class=review-pictures]").select("ul")
					.select("li");
			String imagePath =userid + "\\" +rid  + "\\";
			if (e.size() > 1) {

				for (Element element : e) {
					String image = element.select("a").select("img").attr("data-big");

					ImageUtils.saveToFile(image, imagePath,ImageType.MEMBER);
				}
			}
			Review review = new Review();
			review.setUser(user);
			review.setUserid(userid);
			if (avatar.indexOf("%") > 0) {
				avatar = avatar.substring(0, avatar.indexOf("%"));
			}

			review.setAvatar(ImageUtils.saveToFile(avatar, rid + "\\",ImageType.RESTO));
			review.setRid(rid);

			review.setContent(content);
			review.setImagePath(ImageUtils.getRootPath(ImageType.MEMBER) + imagePath);

			data.add(review);
		}
		



		return data;
	}
}
