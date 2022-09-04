package com.monitor.api;

import ch.qos.logback.classic.Logger;
import com.monitor.util.AppStatsAggregate;
import com.monitor.service.AppService;
import com.monitor.service.AttendeeService;
import com.monitor.service.impl.MessageServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MonitorController {

	private static final Logger logger
			= (Logger) LoggerFactory.getLogger(MonitorController.class);

	private AppService appService;
	private AttendeeService attendeeService;
	private MessageServiceImpl messageService;

	public MonitorController(AppService appService, AttendeeService attendeeService, MessageServiceImpl messageService) {
		this.appService = appService;
		this.attendeeService = attendeeService;
		this.messageService = messageService;
	}

	@GetMapping("/action-monitor")
	@ResponseBody
	public String monitorApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new AppStatsAggregate(appService.get(),attendeeService.get(),messageService.get()).toString();
	}
}
