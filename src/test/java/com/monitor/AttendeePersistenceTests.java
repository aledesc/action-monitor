package com.monitor;

import com.monitor.domain.Attendee;
import com.monitor.service.AttendeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendeePersistenceTests {

	private Attendee attendee;
	@Autowired
	AttendeeService service;

	@BeforeEach
	public void setupDb() {
		attendee= new Attendee(1,"John");
		service.clean();
		service.save( attendee );
	}

	@Test
	public void saveTest() throws Exception {

		Set<Attendee> set= service.get();
		assertTrue( set.size() == 1 );

		service.save( new Attendee("Mike"));
		set= service.get();
		assertTrue( set.size() == 2 );

		service.delete( new Attendee( 1 ));
		set= service.get();
		assertTrue( set.size() == 1 );

	}

}
