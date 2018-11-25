package com.monica.stock.users.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monica.stock.users.model.Quote;
import com.monica.stock.users.repository.QuotesRepository;
@RestController
@RequestMapping("/rest/db")
public class UsersController {
	
	@Autowired
	private QuotesRepository quotesRepository;
	
	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username){
		 return quotesRepository.findByUsername(username).stream().map(quote->{
			 return quote.getQuote();
		 }).collect(Collectors.toList());
	}
	
	@PostMapping("/add")
	public Quote add(@RequestBody final Quote quote){
		 return quotesRepository.save(quote);
	}
	
	@DeleteMapping("/{username}")
	public String remove(@PathVariable("username") final String username) {
		quotesRepository.deleteByUsername(username);
		return "DELETED !!";
	}
}
