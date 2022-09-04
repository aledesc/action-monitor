package com.monitor;

import com.monitor.db.AppRepository;
import com.monitor.service.AppService;
import com.monitor.util.AppStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppStatsPersistenceTests {

	@Autowired
	AppService service;

	@BeforeEach
	public void setupDb() {
		service.clean();
		service.save( new AppStats() );
	}

	@Test
	public void simpleSaveTest() throws Exception {

		Optional<AppStats> optApp= service.get();
		assertTrue( optApp.isPresent() );

		AppStats as= optApp.get();
		assertTrue( as.getId() == 1 );
	}

	@Test
	public void simpleSaveCleanTest() throws Exception {

		service.clean();
		assertTrue( !service.get().isPresent() );

	}

}
