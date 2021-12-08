package com.forrest.controller;

import java.lang.reflect.Array;
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

import com.alibaba.druid.sql.visitor.functions.Char;
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

	private Array a;

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

	public List<Integer> initData(@Param("tableName") String tableName, int stid, int round) throws Exception {
		HttpClient client = HttpClients.createDefault();
		String baseString = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=";
		// "https://liansai.500.com/index.php?c=score&a=getmatch&stid=13070&round=1"
		String url = baseString + stid + "&round=" + round;
		List<Matches> list = MatchesParse.getData(client, url);
		List<Integer> mids = new ArrayList<>();
		if (list.size() > 0) {
			matchesDao.initMatches(tableName, list);
		}
		for (int i = 0; i < list.size(); i++) {
			Matches matches = list.get(i);
			mids.add(matches.getFid());
		}
		return mids;
	}

	public List<Integer> initData(@Param("tableName") String tableName, int stid, String round) throws Exception {
		HttpClient client = HttpClients.createDefault();
		String baseString = "https://liansai.500.com/index.php?c=score&a=getmatch&stid=";
		String url = baseString + stid + "&round=" + round;
		List<Matches> list = MatchesParse.getData(client, url);
		List<Integer> mids = new ArrayList<>();
		if (list.size() > 0) {
			matchesDao.initMatches(tableName, list);
		}
		for (int i = 0; i < list.size(); i++) {
			Matches matches = list.get(i);
			mids.add(matches.getFid());
		}
		return mids;
	}

	public void updateMatches(@Param("tableName1") String tableName1, int mid) throws Exception {
		HttpClient client = HttpClients.createDefault();

		Matches matches = matchesDao.selectFromMatches(tableName1, mid);

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
			mg.setIcp(yaPan.getAa2());
			mg.setIcpss(yaPan.getAa1());
			mg.setIcpxs(yaPan.getAa3());
			mg.setIzp(yaPan.getA2());
			mg.setIzpss(yaPan.getA1());
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
		 for (int i = 13; i < 17; i++) {
				List<Integer> mids = this.initData("bijia", 17811, i);
				int ii = 1;
				if (mids.size() > 0) {
					for (int j = 0; j < mids.size(); j++) {
						this.updateMatches("bijia", mids.get(j));
						System.out.println("i"+ii++);
					}
				}
		 }



		return "initData";
	}

	@RequestMapping("/initPuChao")
	public String initPuChao() throws Exception {
		for (int j = 8; j < 13; j++) {
			List<Integer> mids = this.initData("puchao", 17858, j);
			int ii = 1;
			if (mids.size() > 0) {
				for (int i = 0; i < mids.size(); i++) {
					this.updateMatches("puchao", mids.get(i));
					System.out.println(ii++);
				}
			}
		}
		
		return "initData";
	}

	@RequestMapping("/initXiJia")
	public String initXiJia() throws Exception {
		for (int i = 8; i < 16; i++) {
			List<Integer> mids = this.initData("xijia", 17831, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("xijia", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		// int ii = 1;
		// List<Matches> list = matchesDao.selectMatches("bijia");
		// for (int i = 0; i < list.size(); i++) {
		// Matches xijia = list.get(i);
		//
		// if (xijia.getScore().equals("2:1") || xijia.getScore().equals("1:0")) {
		// System.out.println(ii);
		// ii = 1;
		// } else {
		// ++ii;
		// }
		// }

		return "initData";
	}

	@RequestMapping("/initYingChao")
	public String initYingChao() throws Exception {
		for (int i =7; i < 14; i++) {
			List<Integer> mids = this.initData("yingchao", 17793, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("yingchao", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		return "initData";
	}

	@RequestMapping("/initYiJia")
	public String initYiJia() throws Exception {
		for (int i = 8; i < 15; i++) {
			List<Integer> mids = this.initData("yijia", 17884, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("yijia", mids.get(j));
					System.out.println( mids.get(j));
				}
			}
		}
		
		

		return "initData";
	}

	@RequestMapping("/initDeJia")
	public String initDeJia() throws Exception {

		for (int i = 1; i < 15; i++) {
			List<Integer> mids = this.initData("dejia", 17800, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("dejia", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		return "initData";
	}

	// 法甲
	@RequestMapping("/initFaJia")
	public String initFaJia() throws Exception {

		for (int i = 1; i < 39; i++) {
			List<Integer> mids = this.initData("fajia", 9854, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("fajia", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		return "initData";
	}
	
	@RequestMapping("/initHeJia")
	public String initHeJia() throws Exception {
		for (int i = 1; i < 35; i++) {
			List<Integer> mids = this.initData("hejia", 13071, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("hejia", mids.get(j));
					System.out.println(ii++);
				}
			}
		}
		
		for (int i = 1; i < 27; i++) {
			List<Integer> mids = this.initData("hejia", 14805, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("hejia", mids.get(j));
					System.out.println(ii++);
				}
			}
		}
		
		for (int i = 1; i < 35; i++) {
			List<Integer> mids = this.initData("hejia", 17794, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("hejia", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		return "initData";
	}

	// 欧冠
	@RequestMapping("/initOuGuan")
	public String initOuGuan() throws Exception {
		String[] strings = new String[] {"a","b","c","d","e","f","g","h","i","j","k","l"};
		for (int i = 0; i < 12; i++) {
			List<Integer> mids = this.initData("ouguan", 17059, strings[i]);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("ouguan", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		for (int i = 1; i < 3; i++) {
			List<Integer> mids = this.initData("ouguan", 17232, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("ouguan", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		for (int i = 1; i < 3; i++) {
			List<Integer> mids = this.initData("ouguan", 17436, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("ouguan", mids.get(j));
					System.out.println(ii++);
				}

			}
		}
		
		for (int i = 1; i < 3; i++) {
			List<Integer> mids = this.initData("ouguan", 17484, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("ouguan", mids.get(j));
					System.out.println(ii++);
				}
			}
		}

		for (int i = 1; i < 2; i++) {
			List<Integer> mids = this.initData("ouguan", 17650, i);
			int ii = 1;
			if (mids.size() > 0) {
				for (int j = 0; j < mids.size(); j++) {
					this.updateMatches("ouguan", mids.get(j));
					System.out.println(ii++);
				}

			}
		}

		return "initData";
	}

	@RequestMapping("/initEurope")
	public String initEurope() throws Exception {
		int ii = 1;
		List<Matches> list = matchesDao.selectMatches("xijia");
		for (int i = 0; i < list.size(); i++) {
			Matches xijia = list.get(i);

			if (xijia.getResult().equals("胜") && Float.parseFloat(xijia.getWlcps()) < Float.parseFloat(xijia.getWlcpf())
					|| xijia.getResult().equals("负")
							&& Float.parseFloat(xijia.getWlcps()) > Float.parseFloat(xijia.getWlcpf())) {
				// System.out.println("热门打出" + ii);
				if (ii > 4) {
					System.out.println("热门间隔" + ii);
				}
				ii = 1;
			} else {
				++ii;
			}

		}

		return "initData";
	}

	@RequestMapping("/initConutry")
	public String initConutry() throws Exception {
		int ii = 1;
		List<Matches> list = matchesDao.selectMatches("xijia");
		for (int i = 0; i < list.size(); i++) {
			Matches xijia = list.get(i);

			if (Float.parseFloat(xijia.getWlcps()) < Float.parseFloat(xijia.getWlcpf())
					&& !xijia.getResult().equals("胜")
					|| Float.parseFloat(xijia.getWlcps()) > Float.parseFloat(xijia.getWlcpf())
							&& !xijia.getResult().equals("负")) {
				System.out.println("冷门" + ii);
				ii = 1;
			} else {
				++ii;
			}

		}

		return "initData";
	}

	@RequestMapping("/getMData")
	public String getMData() {
		return "getData";
	}
}
