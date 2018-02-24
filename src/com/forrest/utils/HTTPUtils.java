package com.forrest.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import com.forrest.parse.RestoParse;

public class HTTPUtils {
	public static HttpResponse getRawHtml(HttpClient client, String personalUrl) {
		HttpGet getMethod = new HttpGet(personalUrl);
		getMethod.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		getMethod.setHeader("Accept-Encoding", "gzip, deflate");
		getMethod.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		getMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
		getMethod.setHeader("Connection", "keep-alive");
//		getMethod.setHeader("", "");
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

		try {
			response = client.execute(getMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	public static String getHTMLData(HttpClient client, String url) throws ParseException, IOException
	{
		HttpResponse response = HTTPUtils.getRawHtml(client, url);
		int statusCode = response.getStatusLine().getStatusCode();
		String htmlDt = null;

		if (statusCode == 200) {
			String entity = EntityUtils.toString(response.getEntity(), "utf-8");
			htmlDt = entity;
			EntityUtils.consume(response.getEntity());
		}else {
			EntityUtils.consume(response.getEntity());
		}
		return htmlDt;
	}
}
