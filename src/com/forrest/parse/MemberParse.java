package com.forrest.parse;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.model.Member;
import com.forrest.utils.HTTPUtils;

public class MemberParse {
	public static Member getData(HttpClient client, String url) throws Exception {
		int userid = Integer.parseInt(url.substring(url.lastIndexOf("/") + 1));
		Member member = new Member();
		member.setUserid(userid);

		Document doc = Jsoup.parse(HTTPUtils.getHTMLData(client, url));
		Elements elements = doc.select("span[id=J_usertag]").select("em");
		String tags = elements.text();
		member.setTags(tags);
		member.setCity(doc.select("div[class=user-info col-exp]").select("span[class=user-groun]").text());
		Elements nElements = doc.select("div[class=pic-txt head-user]").select("div[class=txt]")
				.select("div[class=tit]").select("h2");
		member.setName(nElements.text());

		Elements oElements = doc.select("div[class=user_atten]").select("ul").select("li");
		member.setFollowers(oElements.get(0).select("strong").text());
		member.setFans(oElements.get(1).select("strong").text());

		Elements oElements1 = doc.select("div[class=user-time]").select("p");

		member.setContribution_value(oElements1.get(0).select("span[id=J_col_exp]").text());
		member.setCommunity_level(oElements1.get(1).select("p").text());
		member.setRegister_date(oElements1.get(2).select("p").text().substring(5));
		return member;
	}
}
