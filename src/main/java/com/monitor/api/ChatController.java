package com.monitor.api;

import ch.qos.logback.classic.Logger;
import com.monitor.domain.Attendee;
import com.monitor.domain.Message;
import com.monitor.service.AppService;
import com.monitor.service.AttendeeService;
import com.monitor.service.MessageService;
import com.monitor.util.AknowledgeMessage;
import com.monitor.util.AppStats;
import com.monitor.domain.Attendees;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.Set;

@RestController
public class ChatController {

	private static final Logger logger
			= (Logger) LoggerFactory.getLogger(ChatController.class);

	private MessageService messageService;
	private AttendeeService attendeeService;
	private AppService appService;

	public ChatController(MessageService messageService, AttendeeService attendeeService, AppService appService) {
		this.messageService = messageService;
		this.attendeeService = attendeeService;
		this.appService = appService;
	}

	@MessageMapping("/message")
	@SendTo("/topic/messages")
	public AknowledgeMessage talk(Message message) throws Exception {
		Thread.sleep(100); // simulated delay

		logger.info("Storing received message from -> " + message.toString() );
		messageService.save(message);

		logger.info("Delivering received message from -> " + message.toString() );
		return new AknowledgeMessage(HtmlUtils.htmlEscape(message.toString()));
	}


	@MessageMapping("/attending")
	@SendTo("/topic/attendees")
	public Attendees connected(Attendee attendee) throws Exception {

		long manyConnected= attendeeService.count();
		if( manyConnected == 0 )
		{
			appService.clean();
			appService.save( new AppStats() );
		}

		attendeeService.save(attendee);

		logger.info("Delivering list of attendee connected  -> " + attendee.getName() );
		return new Attendees(attendeeService.get());
	}

	@MessageMapping("/attending-nope")
	@SendTo("/topic/attendees")
	public Set<Attendee> disconnected(Attendee attendee) throws Exception {

		attendeeService.delete(attendee);

		logger.info("Delivering list of attendees still connected -> " + attendee.getName() );
		return attendeeService.get();
	}

}
