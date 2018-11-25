package com.monica.stock.data.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.monica.stock.data.model.StockDetails;
import com.monica.stock.data.repository.StockDetailsRepository;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	StockDetailsRepository stockDetailsRepository;
	
	@GetMapping("/{username}")
	public List<StockDetails> getStock(@PathVariable("username")final String username){
		ResponseEntity<List<String>> quoteResponse=restTemplate.exchange("http://"+environment.getProperty("USER_SERVICE_HOST")+":8300/rest/db/"+username, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() {
				});
		
		List<String> quotes=quoteResponse.getBody();
		return quotes.stream().map(quote -> getStockDetails(username,quote)).collect(Collectors.toList());
	}
	
	@GetMapping("/all")
	public List<StockDetails> getAll(){
		return stockDetailsRepository.findAll();
	}
	private StockDetails getStockDetails(String username,String quote) {
		try {
			
			StockDetails sd=new StockDetails();
			Stock sk=YahooFinance.get(quote);
			sd.setUsername(username);
			sd.setSymbol(quote);
			sd.setName(sk.getName());
			sd.setCurrency(sk.getCurrency());
			sd.setPrice(sk.getQuote().getPrice());
			stockDetailsRepository.save(sd);
			return sd;
		} catch (IOException e) {
			e.printStackTrace();
			return new StockDetails();
		}
	}
}
