package com.forrest.controller;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.CookStyleDao;
import com.forrest.dao.IPMessageDao;
import com.forrest.model.CityInfo;
import com.forrest.model.CookStyle;
import com.forrest.model.IPMessage;
import com.forrest.parse.CityInfoParse;
import com.forrest.parse.CookStyleParse;
import com.forrest.utils.IPFilter;
import com.forrest.utils.IPPool;
import com.forrest.utils.IPThread;
import com.forrest.utils.IPUtils;
import com.forrest.utils.URLFecter;

@Controller
public class CookStyleController {

	private CookStyleDao cookStyleDao;
	
	@Autowired
	private IPMessageDao ipMessageDao;
	
	@Autowired
	public CookStyleController(CookStyleDao cookStyleDao) {
		this.cookStyleDao = cookStyleDao;
	}
	
	@RequestMapping("/addCookStyles")
	public String addCookStyles() throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		String rootUrlString = "http://www.dianping.com/beijing/food";


			List<CookStyle> list = CookStyleParse.getData(client, rootUrlString);
			if (list.size() > 0) {
				cookStyleDao.insertCookStyles(list);;
			}

		return "addCookStyles";
	}
	
	@RequestMapping("/addIps")
	public String addIps() throws Exception
	{
		  //�����ȡ������ip��Ϣ
        List<IPMessage> ipMessages = new ArrayList<>();


        //����ʹ�ñ���ip��ȡxici��������һҳ
        for (int i = 1; i < 3; i++) {
            ipMessages = URLFecter.urlParse(ipMessages,i);
            ipMessageDao.insertIPs(ipMessages);

		}

//        //�Եõ���IP����ɸѡ����IP�ٶ����������ڵĲ���������https�����£�����ɾ��
//        ipMessages = IPFilter.Filter(ipMessages);
//
//        //���õ���ip����������⣬���������ϸ��ip��List�����ɾ��
//        IPUtils.IPIsable(ipMessages);

//        //��������url(4000��ip)
//        for (int i = 2; i <= 2; i++) {
//            urls.add("http://www.xicidaili.com/nn/" + i);
//        }
//
//        /**
//         * ��urls���н��������й���,�õ�����Ŀ��IP(ʹ�ö��߳�)
//         *
//         * ����˼·�Ǹ�ÿ���̷߳����Լ������������������List<IPMessage> ipMessages
//         * Ӧ���ǹ��������ÿ���̸߳����������ݵ�ʱ��Ӧ��ע���̰߳�ȫ
//         */
//        IPPool ipPool = new IPPool(ipMessages);
//        for (int i = 0; i < 2; i++) {
//            //��ÿ���߳̽�������ķ���
//            Thread IPThread = new IPThread(urls.subList(i*2, i*2+2), ipPool);
//            threads.add(IPThread);
//            IPThread.start();
//        }
//
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        for(IPMessage ipMessage : ipMessages){
//            out.println(ipMessage.getIPAddress());
//            out.println(ipMessage.getIPPort());
//            out.println(ipMessage.getIPType());
//            out.println(ipMessage.getIPSpeed());
//        }

        //����ȡ������ip��Ϣд��Redis���ݿ���(List����)
        //��redis���ݿ�������ó�һ��IP
//        IPMessage ipMessage = redis.getIPByList();
//        out.println(ipMessage.getIPAddress());
//        out.println(ipMessage.getIPPort());
       

		return "addCookStyles";
	}
	
}
