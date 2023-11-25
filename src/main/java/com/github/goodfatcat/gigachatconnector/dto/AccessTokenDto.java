package com.github.goodfatcat.gigachatconnector.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
	private String access_token;
	private long expires_at;
}
