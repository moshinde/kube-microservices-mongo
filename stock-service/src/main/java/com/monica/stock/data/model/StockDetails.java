package com.monica.stock.data.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="StockDetails")
public class StockDetails {

	@Id
	private String id;
	
	private String username;
	private String symbol;
	private String name;
	private String currency;
	private BigDecimal price;
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public StockDetails(String username, String symbol, String name, String currency, BigDecimal price) {
		super();
		this.username = username;
		this.symbol = symbol;
		this.name = name;
		this.currency = currency;
		this.price = price;
	}
	public StockDetails() {
		super();
	}
	
	
}
