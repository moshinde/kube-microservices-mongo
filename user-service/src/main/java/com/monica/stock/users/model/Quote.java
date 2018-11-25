package com.monica.stock.users.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Quote")
public class Quote {
	@Id
	private String id;
	
	private String username;
	private String quote;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQuote() {
		return quote;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public Quote(String username, String quote) {
		super();
		this.username = username;
		this.quote = quote;
	}
	public Quote() {
		super();
	}
	
	
}
