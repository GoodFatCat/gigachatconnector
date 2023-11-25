package com.github.goodfatcat.gigachatconnector.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
	private String finish_reason;
	private int index;
	private Message message;
}