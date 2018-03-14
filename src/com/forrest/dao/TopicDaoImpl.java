package com.forrest.dao;

import org.springframework.stereotype.Repository;

import com.forrest.mapper.TopicMapper;
import com.forrest.model.Topic;

@Repository
public class TopicDaoImpl implements TopicDao {
	private TopicMapper topicMapper;

	@Override
	public void insertTopic(Topic topic) {
		topicMapper.insertTopic(topic);
	}

}
