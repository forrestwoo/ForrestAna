package com.forrest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forrest.mapper.ReviewMapper;
import com.forrest.model.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public void insertReview(Review review) {
		reviewMapper.insertReview(review);
	}

	@Override
	public void insertReviews(List<Review> list) {
		reviewMapper.insertReviews(list);
	}
}
