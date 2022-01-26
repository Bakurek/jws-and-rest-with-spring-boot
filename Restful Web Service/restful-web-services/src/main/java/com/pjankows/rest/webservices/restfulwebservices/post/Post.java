package com.pjankows.rest.webservices.restfulwebservices.post;

import java.util.Date;

public class Post {
	Integer id;
	String details;
	Date timestamp;

	protected Post() {
		super();
	}
	
	public Post(int id,String details, Date timestamp) {
		super();
		this.id = id;
		this.details = details;
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, details=%s, timestamp=%s]", id, details, timestamp);
	}

	
}
