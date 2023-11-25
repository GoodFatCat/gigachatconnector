package com.github.goodfatcat.gigachatconnector.model;

import java.util.Date;

import com.github.goodfatcat.gigachatconnector.dto.AccessTokenDto;

import lombok.Data;

@Data
public class AccessToken {
	private String accesstoken;
	private Date date;
	
	public AccessToken(AccessTokenDto accessTokenDto) {
		this.accesstoken = "Bearer " + accessTokenDto.getAccess_token();
		this.date = new Date(accessTokenDto.getExpires_at());
	}
}
