package com.github.goodfatcat.gigachatconnector.service;

import com.github.goodfatcat.gigachatconnector.model.AccessToken;
import com.github.goodfatcat.gigachatconnector.model.AnswerResponse;

public interface GigachatService {
	AccessToken updateAccessToken();
	AnswerResponse getAnswerFromGigachat(String question, AccessToken accessToken);
}
