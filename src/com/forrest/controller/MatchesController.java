package com.forrest.controller;

import java.util.Dictionary;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.MatchesDao;
import com.forrest.model.BiFa;
import com.forrest.model.Matches;
import com.forrest.model.MatchesGoal;
import com.forrest.parse.MatchesParse;
import com.forrest.utils.ImageType;
import com.forrest.utils.ImageUtils;

@Controller
public class MatchesController {

	private MatchesDao matchesDao;
	static int fc = 1;

	public MatchesController(MatchesDao matchesDao) {
		this.matchesDao = matchesDao;
	}

	@RequestMapping("/insertMatches")
	public String insertMatches() throws Exception {
		HttpClient client = HttpClients.createDefault();

		/*
		 * 杯赛 int ouguan[] = {9725,11586,12883}; String
		 * u1="https://liansai.500.com/index.php?c=score&a=getmatch&stid="; for (int i =
		 * 0; i < ouguan.length; i++) { String u2=u1+ouguan[i]; for (int j =1; j < 3;
		 * j++) { String u3=u2+"&round="+j; List<Matches> list =
		 * MatchesParse.getData(client, u3);
		 * 
		 * if (list.size() > 0) { matchesDao.insertMatches(list); } } } 主客场round 12 分组0
		 * 决赛1
		 */

		int ouguan[] = { 12379, 13089, 13110, 13128, 13143, 13144 };
		String u1 = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=";
		for (int i = 0; i < ouguan.length; i++) {
			String u2 = u1 + ouguan[i];
			List<Matches> list = MatchesParse.getData(client, u2);
			if (list.size() > 0) {
				matchesDao.insertMatches(list);
			}
		}

		// String baseString =
		// "https://liansai.500.com/index.php?c=score&a=getmatch&stid=";
		// int yijia[] = {7588,8828,10216,11964,13207};
		// for (int i = 0; i < yijia.length; i++) {
		// String u = baseString + yijia[i];
		// for (int j = 1; j < 39; j++) {
		// String url = u + "&round=" + j;
		// List<Matches> list = MatchesParse.getData(client, url);
		//
		// if (list.size() > 0) {
		// matchesDao.insertMatches(list);
		// }
		//
		// String imageurl =
		// "http://liansai.500.com/static/soccerdata/images/TeamPic/teamsignnew_";
		// for (int k = fc; k < fc + 5; k++) {
		// String uuu = imageurl + k + ".png";
		// ImageUtils.saveToFile(uuu, "fc", ImageType.MEMBER);
		// }
		//
		// fc += 5;
		// }
		// }

		// String baseString =
		// "https://liansai.500.com/index.php?c=score&a=getmatch&stid=13195&round=36";
		// List<Matches> list = MatchesParse.getData(client, baseString);
		//
		// if (list.size() > 0)
		// {
		// matchesDao.insertMatches(list);
		// }

		return "insertMatches";
	}

	@RequestMapping("/updateMEvent")
	public String updateMEvent() throws Exception {
		HttpClient client = HttpClients.createDefault();
		/*
		 * 
		 * String rootUrlString =
		 * "https://odds.500.com/fenxi1/inc/stat_ajax.php?act=event&id=748969";
		 * Dictionary<String,String> dictionary =
		 * MatchesParse.getMatchesEventData(client, rootUrlString); MatchesGoal
		 * matchesGoal = new MatchesGoal();
		 * 
		 */

		String rootUrlString = "https://odds.500.com/fenxi1/inc/stat_ajax.php?act=event&id=";
		List<Integer> integers = matchesDao.selectMatcheId();

		for (int i = 0; i < integers.size(); i++) {
			String urlString = rootUrlString + integers.get(i);
			Dictionary<String, String> dictionary = MatchesParse.getMatchesEventData(client, urlString);
			MatchesGoal matchesGoal = new MatchesGoal();
			matchesGoal.setMid(integers.get(i));
			matchesGoal.setZgt(dictionary.get("zg"));
			matchesGoal.setKgt(dictionary.get("kg"));
			matchesDao.updateMEvent(matchesGoal);
			matchesDao.deleteMatches(integers.get(i));
			String imageurl = "http://liansai.500.com/static/soccerdata/images/TeamPic/teamsignnew_";
			for (int j = fc; j < fc + 5; j++) {
				String uuu = imageurl + j + ".png";
				ImageUtils.saveToFile(uuu, "fc", ImageType.MEMBER);
			}

			fc += 5;
		}

		return "updateMEvent";
	}

	@RequestMapping("/updateBiFa")
	public String updateBiFa() throws Exception {
		HttpClient client = HttpClients.createDefault();
		/*
		 * 
		 * String rootUrlString =
		 * "https://odds.500.com/fenxi1/inc/stat_ajax.php?act=event&id=748969";
		 * Dictionary<String,String> dictionary =
		 * MatchesParse.getMatchesEventData(client, rootUrlString); MatchesGoal
		 * matchesGoal = new MatchesGoal();
		 * 
		 */
		// http://odds.500.com/fenxi1/json/ouzhi.php?fid=748991&cid=18
		String rootUrlString = "http://odds.500.com/fenxi1/json/ouzhi.php?fid=";
		List<Integer> integers = matchesDao.selectMatcheId();

		for (int i = 0; i < integers.size(); i++) {
			String urlString = rootUrlString + integers.get(i) + "&cid=18";
			String uu = "https://odds.500.com/fenxi/touzhu-" + integers.get(i) + ".shtml";
			List<Integer> iList = MatchesParse.getBiFaMoneyData(client, uu);
			BiFa biFa = new BiFa();
			biFa.setM1(iList.get(0));
			biFa.setM2(iList.get(1));
			biFa.setM3(iList.get(2));
			
			List<Float> odds = MatchesParse.getBiFaZhiData(client, urlString);
			biFa.setBifa1(odds.get(0));
			biFa.setBifa2(odds.get(1));
			biFa.setBifa3(odds.get(2));
			biFa.setBifa11(odds.get(3));
			biFa.setBifa22(odds.get(4));
			biFa.setBifa33(odds.get(5));
			biFa.setMid(integers.get(i));
			matchesDao.updateBifa(biFa);
			matchesDao.deleteMatches(integers.get(i));

			 String imageurl =
			 "http://liansai.500.com/static/soccerdata/images/TeamPic/teamsignnew_";
			 for (int j = fc; j < fc + 5; j++) {
			 String uuu = imageurl + j + ".png";
			 ImageUtils.saveToFile(uuu, "fc", ImageType.MEMBER);
			 }

			fc += 4;
		}

		return "updateBiFa";
	}
}
