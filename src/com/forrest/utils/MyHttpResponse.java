package com.forrest.utils;

import static java.lang.System.out;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MyHttpResponse {
	 public static String getHtml( String url, String ip, String port) {
	        String entity = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();

	        //���ô�����ʺͳ�ʱ����
	        out.println("��ʱ�߳�: " + Thread.currentThread().getName() + " ��ȡ��ʹ�õĴ���Ϊ: "
	                + ip + ":" + port);
	        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
	                setSocketTimeout(3000).build();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(config);

	        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
	                "q=0.9,image/webp,*/*;q=0.8");
	        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
	        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	        httpGet.setHeader("Cache-Control", "no-cache");
	        httpGet.setHeader("Connection", "keep-alive");
	        httpGet.setHeader("Host", "www.xicidaili.com");
	        httpGet.setHeader("Pragma", "no-cache");
	        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
	        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
	                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	        try {
	            //�ͻ���ִ��httpGet������������Ӧ
	            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

	            //�õ�������Ӧ״̬��
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {
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

	    //����һ�����������أ�ʹ�ñ���ip������վ��ȡ
	    public static String getHtml(String url) {
	        String entity = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();

	        //���ó�ʱ����
	        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
	                setSocketTimeout(3000).build();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(config);

	        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
	                "q=0.9,image/webp,*/*;q=0.8");
	        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
	        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	        httpGet.setHeader("Cache-Control", "no-cache");
	        httpGet.setHeader("Connection", "keep-alive");
	        httpGet.setHeader("Host", "www.xicidaili.com");
	        httpGet.setHeader("Pragma", "no-cache");
	        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
	        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
	                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	        try {
	            //�ͻ���ִ��httpGet������������Ӧ
	            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

	            //�õ�������Ӧ״̬��
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {
	                entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
	            }

	            httpResponse.close();
	            httpClient.close();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return entity;
	    }
}
