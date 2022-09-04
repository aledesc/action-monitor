package com.monitor;

import com.monitor.domain.Message;
import com.monitor.service.AttendeeService;
import com.monitor.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessagePersistenceTests {

	private Message message;

	@Autowired
	MessageService service;

	@BeforeEach
	public void setupDb() {
		message= new Message("John","Hi there!");
		service.clean();
		service.save( message );
	}

	@Test
	public void saveTest() throws Exception {

		Set<Message> set= service.get();
		assertTrue( set.size() == 1 );

		service.save( new Message("John","Hi there! 2"));
		service.save( new Message("John","Hi there! 3"));
		service.save( new Message("John","Hi there! 4"));
		service.save( new Message("John","Hi there! 5"));

		set= service.get();
		assertEquals( 5, set.size() );
	}

}
