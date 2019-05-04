package com.forrest.controller;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.MatchesDao;
import com.forrest.dao.YaPanDao;
import com.forrest.mapper.YaPanMapper;
import com.forrest.model.OuZhi;
import com.forrest.model.YaPan;
import com.forrest.parse.OuZhiParse;
import com.forrest.parse.YaPanParse;
import com.forrest.utils.ImageType;
import com.forrest.utils.ImageUtils;

@Controller
public class YaPanController {

	private YaPanDao yaPanDao;
	
	@Autowired
	private MatchesDao matchesDao;
	
	public YaPanController(YaPanDao yaPanDao)
	{
		this.yaPanDao = yaPanDao;
	}
	
	@RequestMapping("/insertYaPan")
	public String insertYaPan() throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		String rootUrl = "https://odds.500.com/fenxi/daxiao-";
//		String url1 = "https://odds.500.com/fenxi/yazhi-748974.shtml";
		
		List<Integer> mids = matchesDao.selectMatcheId();
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				String url = rootUrl + mids.get(i) + ".shtml";
				System.out.println(url);
				YaPan yaPan = YaPanParse.getData(client, url,mids.get(i));
				if (yaPan != null) {
					yaPanDao.insertYaPan(yaPan);
					matchesDao.deleteMatches(mids.get(i));
				}
				else {
					System.out.println("¿ÕÊý¾Ý");
				}
			}
		}
		return"insertYaPan";
	}
}
