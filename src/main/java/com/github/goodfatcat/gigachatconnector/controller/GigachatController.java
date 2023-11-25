package com.github.goodfatcat.gigachatconnector.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.goodfatcat.gigachatconnector.model.AccessToken;
import com.github.goodfatcat.gigachatconnector.model.AnswerResponse;
import com.github.goodfatcat.gigachatconnector.service.GigachatService;

@RestController
@RequestMapping(path = "/api/gigachat", consumes = MediaType.APPLICATION_JSON_VALUE)
public class GigachatController {

	private AccessToken accessToken;
	@Autowired
	private GigachatService gigachatService;

	@GetMapping("/getAnswer")
	public ResponseEntity<String> getAnswerFromGigachat(@RequestBody String question) {
		Date now = new Date();
		if (accessToken == null || accessToken.getDate().getTime() < now.getTime()) {
			this.accessToken = gigachatService.updateAccessToken();
		}
		
		AnswerResponse response = gigachatService.getAnswerFromGigachat(question, accessToken);
		
		return ResponseEntity.ok(response.getChoices().get(0).getMessage().getContent());
	}
}