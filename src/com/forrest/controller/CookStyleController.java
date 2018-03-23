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
		  //存放爬取下来的ip信息
        List<IPMessage> ipMessages = new ArrayList<>();


        //首先使用本机ip爬取xici代理网第一页
        for (int i = 1; i < 3; i++) {
            ipMessages = URLFecter.urlParse(ipMessages,i);
            ipMessageDao.insertIPs(ipMessages);

		}

//        //对得到的IP进行筛选，将IP速度在两秒以内的并且类型是https的留下，其余删除
//        ipMessages = IPFilter.Filter(ipMessages);
//
//        //对拿到的ip进行质量检测，将质量不合格的ip在List里进行删除
//        IPUtils.IPIsable(ipMessages);

//        //构造种子url(4000条ip)
//        for (int i = 2; i <= 2; i++) {
//            urls.add("http://www.xicidaili.com/nn/" + i);
//        }
//
//        /**
//         * 对urls进行解析并进行过滤,拿到所有目标IP(使用多线程)
//         *
//         * 基本思路是给每个线程分配自己的任务，在这个过程中List<IPMessage> ipMessages
//         * 应该是共享变量，每个线程更新其中数据的时候应该注意线程安全
//         */
//        IPPool ipPool = new IPPool(ipMessages);
//        for (int i = 0; i < 2; i++) {
//            //给每个线程进行任务的分配
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

        //将爬取下来的ip信息写进Redis数据库中(List集合)
        //从redis数据库中随机拿出一个IP
//        IPMessage ipMessage = redis.getIPByList();
//        out.println(ipMessage.getIPAddress());
//        out.println(ipMessage.getIPPort());
       

		return "addCookStyles";
	}
	
}
