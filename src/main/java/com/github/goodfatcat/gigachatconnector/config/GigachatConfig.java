package com.github.goodfatcat.gigachatconnector.config;


import java.util.UUID;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Configuration
public class GigachatConfig {
	private String authData = "Bearer YTFiYzk5NmQtY2EwNy00M2JiLWFhOWYtZDM3YjMzODEzYzQ1OjVlMzQ3NzE0LWQzZTctNDljNC1hODVjLThmMzk5NGM4OWMyYw==";
	
	@Bean
	public HttpClient getHttpClient() throws SSLException {
		SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        
        return HttpClient.create().secure(t -> t.sslContext(sslContext));
	}
	
	@Bean("getAccessToken")
	public WebClient getClientForToken(HttpClient httpClient) {
		return WebClient.builder()
				.baseUrl("https://ngw.devices.sberbank.ru:9443/api/v2/oauth")
				.defaultHeader(HttpHeaders.AUTHORIZATION, authData)
				.defaultHeader("RqUID", UUID.randomUUID().toString())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
	
	@Bean("getAnswer")
	public WebClient getClientForAnswer(HttpClient httpClient) {
		return WebClient.builder()
				.baseUrl("https://gigachat.devices.sberbank.ru/api/v1/chat/completions")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
	
	
}
