package com.github.goodfatcat.gigachatconnector.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AnswerRequest {
	private String model = "GigaChat:latest";
	private double temperature = 0.87;
	private int n = 1;
	private int max_tokens = 512;
	private double repetition_penalty = 1;
	private boolean stream = false;
	private int update_interval = 0;
	private List<Message> messages;
	
	public AnswerRequest(Message message) {
		this.messages = new ArrayList<>();
		messages.add(new Message("system", "Отвечай как помощник андеррайтера"));
		messages.add(message);
	}
}