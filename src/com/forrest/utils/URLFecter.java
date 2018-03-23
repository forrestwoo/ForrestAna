package com.forrest.utils;

import static java.lang.System.out;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.forrest.model.IPMessage;

public class URLFecter {
	 //ʹ�ô��������ȡ
    public static boolean urlParse(String url, String ip, String port,
                                           List<IPMessage> ipMessages1) {
        //����һ����ʹ�䷵��htmlԴ��
        String html = MyHttpResponse.getHtml(url, ip, port);

        if(html != null) {
            //��html������DOM�ṹ
            Document document = Jsoup.parse(html);

            //��ȡ����Ҫ������
            Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");

            for (int i = 1; i < trs.size(); i++) {
                IPMessage ipMessage = new IPMessage();
                String ipAddress = trs.get(i).select("td").get(1).text();
                String ipPort = trs.get(i).select("td").get(2).text();
                String ipType = trs.get(i).select("td").get(5).text();
                String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar]").
                        attr("title");

                ipMessage.setIPAddress(ipAddress);
                ipMessage.setIPPort(ipPort);
                ipMessage.setIPType(ipType);
                ipMessage.setIPSpeed(ipSpeed);


                ipMessages1.add(ipMessage);
            }

            return true;
        } else {
            out.println(ip+ ": " + port + " ��������");

            return false;
        }
    }

    //ʹ�ñ���IP��ȡxici������վ�ĵ�һҳ
    public static List<IPMessage> urlParse(List<IPMessage> ipMessages,int page) {
        String url = "http://www.xicidaili.com/nn/" +page;
        String html = MyHttpResponse.getHtml(url);

        //��html������DOM�ṹ
        Document document = Jsoup.parse(html);

        //��ȡ����Ҫ������
        Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");

        for (int i = 1; i < trs.size(); i++) {
            IPMessage ipMessage = new IPMessage();
            String ipAddress = trs.get(i).select("td").get(1).text();
            String ipPort = trs.get(i).select("td").get(2).text();
            String ipType = trs.get(i).select("td").get(5).text();
            String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar]").
                    attr("title");

            ipMessage.setIPAddress(ipAddress);
            ipMessage.setIPPort(ipPort);
            ipMessage.setIPType(ipType);
            ipMessage.setIPSpeed(ipSpeed);

            ipMessages.add(ipMessage);
        }

        return ipMessages;
    }
}
