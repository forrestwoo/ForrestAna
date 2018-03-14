package com.forrest.mapper;

import java.util.List;

import com.forrest.model.Review;

public interface ReviewMapper {
	public void insertReview(Review review);
	public void insertReviews(List<Review> list);
}
