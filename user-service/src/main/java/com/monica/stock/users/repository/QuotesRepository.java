package com.monica.stock.users.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monica.stock.users.model.Quote;

public interface QuotesRepository extends MongoRepository<Quote, String>{

	List<Quote> findByUsername(String username);

	void deleteByUsername(String username);
	
}
