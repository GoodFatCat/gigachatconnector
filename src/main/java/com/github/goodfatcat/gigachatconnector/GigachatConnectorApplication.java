package com.github.goodfatcat.gigachatconnector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class GigachatConnectorApplication {
	
	@Value("${gigachat.value}")
	public static String value;
	
	public static void main(String[] args) {
		SpringApplication.run(GigachatConnectorApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    System.out.println(value);
	}
}
