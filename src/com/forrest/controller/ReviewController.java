package com.forrest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.RestaurantDao;
import com.forrest.dao.ReviewDao;
import com.forrest.model.Review;
import com.forrest.parse.ReviewParse;
import com.forrest.utils.ForrestUtils;

@Controller
public class ReviewController {

	private ReviewDao reviewDao;

	@Autowired
	private RestaurantDao restaurantDao;

	public ReviewController(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@RequestMapping("/addReviews")
	public String addReviews() throws IOException, Exception {
		List<String> ridList = restaurantDao.selectRidFromResto();
		String urlString = "http://www.dianping.com/shop/";

		for (int i = 4; i < ridList.size(); i++) {
			String string = "18215228";
			//
			// 60293457
			String url = urlString + string + "/review_all";

			int page = ForrestUtils.getPageCount(HttpClients.createDefault(), url, "div[class=reviews-pages]");

			List<Review> list = ReviewParse.getData(HttpClients.createDefault(), url, string);

			if (page == 0) {
				continue;
			} else if (page == 1 && list != null && list.size() > 0) {
				reviewDao.insertReviews(list);
			} else if (list != null && list.size() > 0) {
				for (int j = 42; j < page + 1; j++) {
					String newUrl = url + "/p" + j;
					System.out.println("网站地址：" + newUrl);
					System.out.println("索引ID：" + j);

					reviewDao.insertReviews(ReviewParse.getData(HttpClients.createDefault(), newUrl, string));
				}
			}
		}
		return "addRestos";
	}

	@RequestMapping("/getRestoPhotos")
	public String getRestoPhotos() throws Exception, Exception {
		// http://www.dianping.com/shop/10007873/photos
		List<String> ridList = restaurantDao.selectRidFromResto();
		String urlString = "http://www.dianping.com";
		for (int i = 0; i < ridList.size(); i++) {
			String string = ridList.get(i);
			// 会员相册
			String url = urlString + "/shop/" + string + "/photos/album";
			// 官方相册

			int page = ForrestUtils.getPageCount(HttpClients.createDefault(), url, "div[class=Pages]");
			int p = 1 < page ? 1:page;
			for (int j = 1; j < p + 1; j++) {
				String fullUrl = url + "?pg=" + j;
				List<String> urList = ReviewParse.getUrls(HttpClients.createDefault(), fullUrl);
				List<Review> reviews =new ArrayList<Review>();
				for (int k = 0; k < urList.size(); k++) {
					String uu = urList.get(k);
					Review review = ReviewParse.getReview(HttpClients.createDefault(),urlString + uu, string);
					System.out.println("网站地址为：" + fullUrl);
					reviews.add(review);
				}
				
				reviewDao.insertReviews(reviews);
			}
		}
		return "addRestos";
	}
}
