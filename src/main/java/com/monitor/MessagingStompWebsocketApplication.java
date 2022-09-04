package com.monitor;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingStompWebsocketApplication {
	private static final Logger logger
			= (Logger) LoggerFactory.getLogger(MessagingStompWebsocketApplication.class);

	public static void main(String[] args) {
		logger.info("Up and running the boot for messaging with WebSockets ! ");
		SpringApplication.run(MessagingStompWebsocketApplication.class, args);
	}
}
