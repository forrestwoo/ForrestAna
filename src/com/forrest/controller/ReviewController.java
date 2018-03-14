package com.forrest.controller;

import java.io.IOException;
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
	public String addReviews() throws IOException, Exception
	{
		List<String> ridList = restaurantDao.selectRidFromResto();
		String urlString = "http://www.dianping.com/shop/";
		for (int i = 0; i < 1; i++) {
			String string = ridList.get(i);
			//
			//60293457
			String url = urlString + "10011314" + "/review_all";

			int page = ForrestUtils.getPageCount(HttpClients.createDefault(), url, "div[class=reviews-pages]");
			List<Review> list= ReviewParse.getData(HttpClients.createDefault(), url, "60293457");

			if (page == 0) {
				continue;
			} else if (page == 1 && list != null && list.size() >0) {
				reviewDao.insertReviews(list);
			} else  if (list != null && list.size() >0) {
				for (int j = 22; j < page + 1; j++) {
					String newUrl = url + "/p" + j;
					System.out.println("ÍøÕ¾µØÖ·£º" + newUrl);
					System.out.println("Ë÷ÒýID£º" + j);
					reviewDao.insertReviews(ReviewParse.getData(HttpClients.createDefault(), newUrl, "60293457"));
				}
			}
		}
		return "addRestos";
	}
}
