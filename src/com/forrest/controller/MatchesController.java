package com.forrest.controller;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.forrest.dao.MatchesDao;
import com.forrest.dao.OuZhiDao;
import com.forrest.model.BiFa;
import com.forrest.model.Matches;
import com.forrest.model.MatchesGoal;
import com.forrest.model.OuZhi;
import com.forrest.model.YaPan;
import com.forrest.parse.BaiduImageParse;
import com.forrest.parse.MatchesParse;
import com.forrest.parse.OuZhiParse;
import com.forrest.utils.ImageType;
import com.forrest.utils.ImageUtils;

@Controller
public class MatchesController {

	private MatchesDao matchesDao;

	@Autowired
	private OuZhiDao ouZhiDao;
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

	public void initData(@Param("tableName") String tableName, int stid, int round) throws Exception {
		HttpClient client = HttpClients.createDefault();
		String baseString = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=";
		// "https://liansai.500.com/index.php?c=score&a=getmatch&stid=13070&round=1"
		String url = baseString + stid + "&round=" + round;
		List<Matches> list = MatchesParse.getData(client, url);

		if (list.size() > 0) {
			matchesDao.initMatches(tableName, list);
		}
	}

	public void initOuZhi(@Param("tableName") String tableName, int mid) throws Exception {
		HttpClient client = HttpClients.createDefault();
		String baseUrl = "http://odds.500.com/fenxi1/json/ouzhi.php?fid=";
		String rootUrl = "";
		// http://odds.500.com/fenxi1/json/ouzhi.php?fid=748929&cid=293
		List<Integer> oddCompany = new ArrayList<Integer>();
		oddCompany.add(293);
		oddCompany.add(4);
		oddCompany.add(6);
		oddCompany.add(8);
		oddCompany.add(18);

		OuZhi ouZhi = new OuZhi();
		rootUrl = baseUrl + mid;
		List<Float> odds1 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(0));
		List<Float> odds2 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(1));
		List<Float> odds3 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(2));
		List<Float> odds4 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(3));
		List<Float> odds5 = OuZhiParse.getData(client, rootUrl + "&cid=" + oddCompany.get(4));

		ouZhi.setMid(mid);
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

		ouZhi.setBifa1(odds5.get(0));
		ouZhi.setBifa2(odds5.get(1));
		ouZhi.setBifa3(odds5.get(2));
		ouZhi.setBifa11(odds5.get(3));
		ouZhi.setBifa22(odds5.get(4));
		ouZhi.setBifa33(odds5.get(5));

		ouZhiDao.initOuZhi(tableName, ouZhi);
	}

	@RequestMapping("/updateBiFa")
	public String updateBiFa() throws Exception {
		HttpClient client = HttpClients.createDefault();

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

			String imageurl = "http://liansai.500.com/static/soccerdata/images/TeamPic/teamsignnew_";
			for (int j = fc; j < fc + 5; j++) {
				String uuu = imageurl + j + ".png";
				ImageUtils.saveToFile(uuu, "fc", ImageType.MEMBER);
			}

			fc += 4;
		}

		return "updateBiFa";
	}

	@RequestMapping("/updateMEvent")
	public String updateMEvent() throws Exception {
		HttpClient client = HttpClients.createDefault();

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

	public void updateMatches(@Param("tableName1") String tableName1, @Param("tableName2") String tableName2, int mid)
			throws Exception {
		HttpClient client = HttpClients.createDefault();

		Matches matches = matchesDao.selectFromMatches(tableName2, mid);
		System.out.println(matches.getHname() + matches.getGname() + matches.getGscore());

		MatchesGoal mg = new MatchesGoal();

		// 基本比赛数据
		mg.setMid(mid);
		mg.setZhudui(matches.getHname());
		mg.setKedui(matches.getGname());
		mg.setKs(matches.getGscore());
		mg.setZh(matches.getHhalfscore());
		mg.setZs(matches.getHscore());
		mg.setKh(matches.getGhalfscore());
		mg.setScore(matches.getHscore() + ":" + matches.getGscore());
		int z = matches.getHscore();
		int k = matches.getGscore();
		String result = "";
		if (z > k)
			result = "胜";
		else if (z == k)
			result = "平";
		else
			result = "负";
		mg.setResult(result);

		// 必发胜平负盈亏
		String touzhu = "https://odds.500.com/fenxi/touzhu-" + mid + ".shtml";
		List<Integer> iList = MatchesParse.getBiFaMoneyData(client, touzhu);
		mg.setM1(iList.get(0));
		mg.setM2(iList.get(1));
		mg.setM3(iList.get(2));

		// 进球时间 如果赛果为0：0则不更新进球时间
		/*
	if (z != 0 || k != 0) {
			String event = "https://odds.500.com/fenxi1/inc/stat_ajax.php?act=event&id=" + mid;
			Dictionary<String, String> dictionary = MatchesParse.getMatchesEventData(client, event);
			mg.setZgt(dictionary.get("zg"));
			mg.setKgt(dictionary.get("kg"));
		}
		 * */
	
		String yazhi = "https://odds.500.com/fenxi/yazhi-" + mid + ".shtml";
		YaPan yaPan = MatchesParse.getYaPanData(client, yazhi, mid);
		if (yaPan != null) {
			mg.setA1(yaPan.getA1());
			mg.setA2(yaPan.getA2());
			mg.setA3(yaPan.getA3());
			mg.setAa1(yaPan.getAa1());
			mg.setAa2(yaPan.getAa2());
			mg.setAa3(yaPan.getAa3());
			mg.setB1(yaPan.getB1());
			mg.setB2(yaPan.getB2());
			mg.setB3(yaPan.getB3());
			mg.setBb1(yaPan.getBb1());
			mg.setBb2(yaPan.getBb2());
			mg.setBb3(yaPan.getBb3());
		}

		String daxiao = "https://odds.500.com/fenxi/daxiao-" + mid + ".shtml";
		YaPan daxiaoqiu = MatchesParse.getDaXiaoData(client, daxiao, mid);
		if (daxiaoqiu != null) {
			mg.setC1(daxiaoqiu.getA1());
			mg.setC2(daxiaoqiu.getA2());
			mg.setC3(daxiaoqiu.getA3());
			mg.setCc1(daxiaoqiu.getAa1());
			mg.setCc2(daxiaoqiu.getAa2());
			mg.setCc3(daxiaoqiu.getAa3());
			mg.setD1(daxiaoqiu.getB1());
			mg.setD2(daxiaoqiu.getB2());
			mg.setD3(daxiaoqiu.getB3());
			mg.setDd1(daxiaoqiu.getBb1());
			mg.setDd2(daxiaoqiu.getBb2());
			mg.setDd3(daxiaoqiu.getBb3());
		}

		matchesDao.updateMatches(tableName1, tableName2, mg);
	}

	@RequestMapping("/initYingChao")
	public String initYingChao() throws Exception {
		this.initData("premier", 14789, 13);
		List<Integer> mids = matchesDao.selectMidFromMatches("premier", "yingchao");

		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.initOuZhi("yingchao", mids.get(i));
				this.updateMatches("yingchao", "premier", mids.get(i));
			}
		}

		return "initData";
	}

	@RequestMapping("/initYiJia")
	public String initYiJia() throws Exception {
		this.initData("seriea", 15160, 13);
		List<Integer> mids = matchesDao.selectMidFromMatches("seriea", "yijia");

		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.initOuZhi("yijia", mids.get(i));
				this.updateMatches("yijia", "seriea", mids.get(i));
			}
		}

		return "initData";
	}

	@RequestMapping("/initXiJia")
	public String initXiJia() throws Exception {
		
		
		this.initData("laliga", 14981, 19);
		List<Integer> mids = matchesDao.selectMidFromMatches("laliga", "xijia");

		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.initOuZhi("xijia", mids.get(i));
				this.updateMatches("xijia", "laliga", mids.get(i));
			}
		}

		return "initData";
	}

	@RequestMapping("/initDeJia")
	public String initDeJia() throws Exception {

		return "initData";
	}

	// 俄超
	@RequestMapping("/initFaJia")
	public String initFaJia() throws Exception {
		
		
		 this.initData("ligue1", 14783, 12);
		List<Integer> mids = matchesDao.selectMidFromMatches("ligue1", "fajia");

		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.initOuZhi("fajia", mids.get(i));
				this.updateMatches("fajia", "ligue1", mids.get(i));
			}
		}
		 
		/**
		for (int m = 6; m < 31; m++) {
			this.initData("ligue1", 11750, m);
			List<Integer> mids = matchesDao.selectMidFromMatches("ligue1", "fajia");

			if (mids.size() > 0) {
				for (int i = 0; i < mids.size(); i++) {
					this.initOuZhi("fajia", mids.get(i));
					this.updateMatches("fajia", "ligue1", mids.get(i));
				}
			}
		}
		 * */
	
	

		return "initData";
	}

	@RequestMapping("/initEurope")
	public String initEurope() throws Exception {
		// 欧冠
		this.initData("champions", 15276, 1);
		this.initData("champions", 15276, 2);


		// 欧联
		this.initData("champions", 15296, 1);
		this.initData("champions", 15296, 2);
		this.initData("champions", 15180, 1);
		this.initData("champions", 15180, 2);
		List<Integer> mids = matchesDao.selectMidFromMatches("champions", "europe");

		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.initOuZhi("europe", mids.get(i));
				this.updateMatches("europe", "champions", mids.get(i));
			}
		}

		return "initData";
	}

	@RequestMapping("/initConutry")
	public String initConutry() throws Exception {
		this.initData("europecup", 13207, 38);
		List<Integer> mids = matchesDao.selectMidFromMatches("europecup", "country");

		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.initOuZhi("country", mids.get(i));
				this.updateMatches("country", "europecup", mids.get(i));
			}
		}

		return "initData";
	}

	@RequestMapping("/getMData")
	public String getMData() {
		return "getData";
	}
}
