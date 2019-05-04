package com.forrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.MatchesDao;
import com.forrest.dao.OuZhiDao;
import com.forrest.model.OuZhi;
import com.forrest.parse.OuZhiParse;
import com.forrest.utils.ImageType;
import com.forrest.utils.ImageUtils;

@Controller
public class OuZhiController {

	private OuZhiDao ouZhiDao;
	static int fc = 871;
	
	@Autowired
	private MatchesDao matchesDao;

	public OuZhiController(OuZhiDao ouZhiDao) {
		this.ouZhiDao = ouZhiDao;
	}

	@RequestMapping("/insertOuZhi")
	public String insertOuZhi() throws Exception {
		HttpClient client = HttpClients.createDefault();
		String baseUrl = "http://odds.500.com/fenxi1/json/ouzhi.php?fid=";
		String rootUrl = "";
		// http://odds.500.com/fenxi1/json/ouzhi.php?fid=748929&cid=293
		List<Integer> oddCompany = new ArrayList<Integer>();
		oddCompany.add(293);
		oddCompany.add(4);
		oddCompany.add(6);
		oddCompany.add(8);

		List<Integer> mids = matchesDao.selectMatcheId();
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				OuZhi ouZhi = new OuZhi();
				rootUrl = baseUrl + mids.get(i);
				List<Float> odds1 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(0));
				List<Float> odds2 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(1));
				List<Float> odds3 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(2));
				List<Float> odds4 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(3));
				// System.out.println(rootUrl + "&cid=" + oddCompany.get(0));
				// System.out.println(rootUrl + "&cid=" + oddCompany.get(1));
				// System.out.println(rootUrl + "&cid=" + oddCompany.get(2));
				// System.out.println(rootUrl + "&cid=" + oddCompany.get(3));
				ouZhi.setMid(mids.get(i));
				ouZhi.setA1(odds1.get(0));
				ouZhi.setA2(odds1.get(1));
				ouZhi.setA3(odds1.get(2));
				ouZhi.setAa1(odds1.get(3));
				ouZhi.setAa2(odds1.get(4));
				ouZhi.setAa3(odds1.get(5));

				ouZhi.setB1(odds2.get(0));
				ouZhi.setB2(odds2.get(1));
				ouZhi.setB3(odds2.get(2));
				ouZhi.setBb1(odds2.get(3));
				ouZhi.setBb2(odds2.get(4));
				ouZhi.setBb3(odds2.get(5));

				ouZhi.setC1(odds3.get(0));
				ouZhi.setC2(odds3.get(1));
				ouZhi.setC3(odds3.get(2));
				ouZhi.setCc1(odds3.get(3));
				ouZhi.setCc2(odds3.get(4));
				ouZhi.setCc3(odds3.get(5));

				ouZhi.setD1(odds4.get(0));
				ouZhi.setD2(odds4.get(1));
				ouZhi.setD3(odds4.get(2));
				ouZhi.setDd1(odds4.get(3));
				ouZhi.setDd2(odds4.get(4));
				ouZhi.setDd3(odds4.get(5));
				ouZhiDao.insertOuZhi(ouZhi);

				matchesDao.deleteMatches(mids.get(i));
				String imageurl="http://liansai.500.com/static/soccerdata/images/TeamPic/teamsignnew_";
				for (int j = fc; j < fc+5; j++) {
					String uuu=imageurl+j+".png";
					ImageUtils.saveToFile(uuu, "fc", ImageType.MEMBER);
				}
				
				fc+=5;
			}
		}

		return "insertOuZhi";
	}
}
