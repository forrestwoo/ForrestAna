package com.forrest.dao;

import java.util.List;

import com.forrest.model.Review;

public interface ReviewDao {
	public void insertReviews(List<Review> list);
}