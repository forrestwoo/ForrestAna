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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.forrest.dao.MatchesDao;
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

	public void updateMatches(@Param("tableName1") String tableName1, int mid) throws Exception {
		HttpClient client = HttpClients.createDefault();

		Matches matches = matchesDao.selectFromMatches(tableName1, mid);
		Document document = Jsoup.connect("http://odds.500.com/fenxi/shuju-993362.shtml").get();
		// System.out.println(matches.getHname() + matches.getGname() +
		// matches.getGscore());

		MatchesGoal mg = new MatchesGoal();

		// 基本比赛数据
		mg.setMid(mid);
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
		String yazhi = "https://odds.500.com/fenxi/yazhi-" + mid + ".shtml";
		YaPan yaPan = MatchesParse.getYaPanData(client, yazhi, mid);
		if (yaPan != null) {
			mg.setIcp(yaPan.getAa1());
			mg.setIcpss(yaPan.getAa2());
			mg.setIcpxs(yaPan.getAa3());
			mg.setIzp(yaPan.getA1());
			mg.setIzpss(yaPan.getA2());
			mg.setIzpxs(yaPan.getA3());
		}
		String daxiao = "https://odds.500.com/fenxi/daxiao-" + mid + ".shtml";
		YaPan daxiaoqiu = MatchesParse.getDaXiaoData(client, daxiao, mid);
		if (daxiaoqiu != null) {

			mg.setIdxp(daxiaoqiu.getAa2());
			mg.setIdxpd(daxiaoqiu.getAa1());
			mg.setIdxpx(daxiaoqiu.getAa3());
		}
		// http://odds.500.com/fenxi/ouzhi-988158.shtml
		String ou = "http://odds.500.com/fenxi/ouzhi-" + mid + ".shtml";
		OuZhi ouzhi = MatchesParse.getOuZhiData(client, ou, mid);
		mg.setWlcps(ouzhi.getA1());
		mg.setWlcpp(ouzhi.getA2());
		mg.setWlcpf(ouzhi.getA3());
		mg.setWlzps(ouzhi.getAa1());
		mg.setWlzpp(ouzhi.getAa2());
		mg.setWlzpf(ouzhi.getAa3());
		mg.setIcps(ouzhi.getB1());
		mg.setIcpp(ouzhi.getB2());
		mg.setIcpf(ouzhi.getB3());
		mg.setIzps(ouzhi.getBb1());
		mg.setIzpp(ouzhi.getBb2());
		mg.setIzpf(ouzhi.getBb3());

		matchesDao.updateMatches(tableName1, mg);
	}

	@RequestMapping("/initBiJia")
	public String initBiJia() throws Exception {
		// for (int i = 1; i < 4; i++) {
		// this.initData("bijia", 17811, i);
		// }
		// this.initData("bijia", 17811, 1);

		// this.updateMatches("bijia", 988158);
		List<Integer> mids = matchesDao.selectMidFromMatches("bijia", "xijia");
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("bijia", mids.get(i));
			}
		}
		/***
		 * List<Integer> mids = matchesDao.selectMidFromMatches("laliga", "xijia");
		 * 
		 * if (mids.size() > 0) { for (int i = 0; i < mids.size(); i++) {
		 * this.initOuZhi("xijia", mids.get(i)); this.updateMatches("xijia", "laliga",
		 * mids.get(i)); }
		 * 
		 * 
		 * }
		 * 
		 */

		return "initData";
	}

	@RequestMapping("/initYingChao")
	public String initYingChao() throws Exception {
		List<Integer> mids = matchesDao.selectMidFromMatches("yingchao", "xijia");
		int ii = 0;
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("yingchao", mids.get(i));
				System.out.println(ii++);
			}
		}
		// for (int i = 1; i < 39; i++) {
		// this.initData("yingchao", 16907, i);
		// }

		return "initData";
	}

	@RequestMapping("/initYiJia")
	public String initYiJia() throws Exception {
		// for (int i = 1; i < 39; i++) {
		// this.initData("yijia", 16967, i);
		// }
		List<Integer> mids = matchesDao.selectMidFromMatches("yijia", "xijia");
		int ii = 0;
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("yijia", mids.get(i));
				System.out.println(ii++);
			}
		}
		return "initData";
	}

	@RequestMapping("/initXiJia")
	public String initXiJia() throws Exception {

		// for (int i = 1; i < 39; i++) {
		// this.initData("xijia", 16939, i);
		// }
		List<Integer> mids = matchesDao.selectMidFromMatches("xijia", "xijia");
		int ii = 0;
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("xijia", mids.get(i));
				System.out.println(ii++);
			}
		}

		return "initData";
	}

	@RequestMapping("/initDeJia")
	public String initDeJia() throws Exception {
//		for (int i = 1; i < 39; i++) {
//			this.initData("dejia", 16855, i);
//		}
		List<Integer> mids = matchesDao.selectMidFromMatches("dejia", "xijia");
		int ii = 0;
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("dejia", mids.get(i));
				System.out.println(ii++);
			}
		}
		
		return "initData";
	}

	@RequestMapping("/initPuChao")
	public String initPuChao() throws Exception {
//		for (int i = 1; i < 39; i++) {
//			this.initData("puchao", 16966, i);
//		}
		List<Integer> mids = matchesDao.selectMidFromMatches("puchao", "xijia");
		int ii = 0;
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("puchao", mids.get(i));
				System.out.println(ii++);
			}
		}
		return "initData";
	}

	// 俄超
	@RequestMapping("/initFaJia")
	public String initFaJia() throws Exception {
//		for (int i = 1; i < 39; i++) {
//			this.initData("fajia", 16764, i);
//		}

		List<Integer> mids = matchesDao.selectMidFromMatches("fajia", "xijia");
		int ii = 0;
		if (mids.size() > 0) {
			for (int i = 0; i < mids.size(); i++) {
				this.updateMatches("fajia", mids.get(i));
				System.out.println(ii++);
			}
		}

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
				// this.updateMatches("europe", "champions", mids.get(i));
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
				// this.updateMatches("country", "europecup", mids.get(i));
			}
		}

		return "initData";
	}

	@RequestMapping("/getMData")
	public String getMData() {
		return "getData";
	}
}
