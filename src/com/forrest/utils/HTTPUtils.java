package com.forrest.utils;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

public class HTTPUtils {
	public static HttpResponse getRawHtml(HttpClient client, String personalUrl) {
		HttpGet getMethod = new HttpGet(personalUrl);
		getMethod.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		getMethod.setHeader("Accept-Encoding", "gzip, deflate");
		getMethod.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		getMethod.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
		getMethod.setHeader("Connection", "keep-alive");
		getMethod.setHeader("Cookie", "_lxsdk_cuid=161237b805fc8-083de32a27f10f-454f032b-1fa400-161237b805fc8; _lxsdk=161237b805fc8-083de32a27f10f-454f032b-1fa400-161237b805fc8; "
				+ "_hc.v=ae8cf27d-8bc1-e7a9-4368-79bb76f56ef0.1516718752; aburl=1; s_ViewType=10; __utmz=1.1517295162.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); "
				+ "looyu_id=c35fe27168484305b85a1f854a69679637_51868%3A2; __utma=1.1292573773.1517295162.1518368270.1518455814.4; cityid=2; "
				+ "Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1517682814,1519456349; ua=jiyidechengxu; ctu=67ef88871a731e00b179b33493e6f9daefc3e981c1aefda99a3c7fb46b25e797; "
				+ "__utmz=1.1517295162.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=1.1292573773.1517295162.1518368270.1518455814.4; "
				+ "switchcityflashtoast=1; default_ab=shop%3AA%3A1%7Cindex%3AA%3A1%7CshopList%3AA%3A1%7Cshopreviewlist%3AA%3A1%7Csinglereview%3AA%3A1; "
				+ "cy=2; cye=beijing; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_s=1624919ccd3-94-a1d-6a5%7C%7C87");
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

		try {
			response = client.execute(getMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String getHTMLData(HttpClient client, String url) throws ParseException, IOException {
		HttpResponse response = HTTPUtils.getRawHtml(client, url);
		int statusCode = response.getStatusLine().getStatusCode();
		String htmlDt = null;

		if (statusCode == 200) {
			String entity = EntityUtils.toString(response.getEntity(), "GB2312");
			htmlDt = entity;
			EntityUtils.consume(response.getEntity());
		} else {
			EntityUtils.consume(response.getEntity());
			System.out.println(statusCode);

		}
		return htmlDt;
	}
	
	 public static String getHtml( String url, String ip, String port) {
	        String entity = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        CloseableHttpResponse httpResponse = null;
	        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
	                setSocketTimeout(3000).build();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(config);

	        httpGet.setHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
	        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
	        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
	        httpGet.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
	        httpGet.setHeader("Connection", "keep-alive");
//			HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
	        try {
	            //客户端执行httpGet方法，返回响应
	        	httpResponse= httpClient.execute(httpGet);
	            System.out.println("entity"+httpResponse.getEntity());
                System.out.println("httpResponse"+httpResponse);
	            //得到服务响应状态码
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {
	                System.out.println("entity"+httpResponse.getEntity());
	                System.out.println("httpResponse"+httpResponse);

	                entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
	            }

	            httpResponse.close();
	            httpClient.close();
	        } catch (ClientProtocolException e) {
	            entity = null;
	        } catch (IOException e) {
	            entity = null;
	        }

	        return entity;
	    }

}
