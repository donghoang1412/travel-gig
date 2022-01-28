package com.synergisticit.domain;

public class MailRequest {
	private  String from;
	private  String to;
	private String body;
	private  String subject;
	
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}

	
}
