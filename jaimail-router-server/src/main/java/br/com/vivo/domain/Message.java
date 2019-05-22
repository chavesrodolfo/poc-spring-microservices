package br.com.vivo.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Message {

	private String text;
	private String app;

	public Message() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
