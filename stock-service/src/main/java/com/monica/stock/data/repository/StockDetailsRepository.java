package com.monica.stock.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monica.stock.data.model.StockDetails;

public interface StockDetailsRepository extends MongoRepository<StockDetails, String> {

}
