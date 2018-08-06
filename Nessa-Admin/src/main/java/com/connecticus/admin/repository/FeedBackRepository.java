package com.connecticus.admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.connecticus.admin.model.FeedBackModel;


public interface FeedBackRepository extends MongoRepository<FeedBackModel, String>  {

}
