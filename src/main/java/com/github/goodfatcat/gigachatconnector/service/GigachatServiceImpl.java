package com.github.goodfatcat.gigachatconnector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.goodfatcat.gigachatconnector.dto.AccessTokenDto;
import com.github.goodfatcat.gigachatconnector.model.AccessToken;
import com.github.goodfatcat.gigachatconnector.model.AnswerRequest;
import com.github.goodfatcat.gigachatconnector.model.AnswerResponse;
import com.github.goodfatcat.gigachatconnector.model.Message;

@Service
public class GigachatServiceImpl implements GigachatService {

	@Autowired
	@Qualifier("getAccessToken")
	private WebClient webClientToGetAccessToken;
	@Autowired
	@Qualifier("getAnswer")
	private WebClient webClientToGetAnswer;

	@Override
	public AccessToken updateAccessToken() {
		AccessToken newAccessToken = new AccessToken(webClientToGetAccessToken
				.post()
				.body(BodyInserters.fromFormData("scope", "GIGACHAT_API_PERS"))
				.retrieve().bodyToMono(AccessTokenDto.class).block());
		return newAccessToken;
	}

	@Override
	public AnswerResponse getAnswerFromGigachat(String question,
			AccessToken accessToken) {
		AnswerRequest answerRequest = new AnswerRequest(
				new Message("user", question));

		AnswerResponse response = webClientToGetAnswer.post()
				.header(HttpHeaders.AUTHORIZATION, accessToken.getAccesstoken())
				.bodyValue(answerRequest).retrieve()
				.bodyToMono(AnswerResponse.class).block();;
		return response;

	}

}
