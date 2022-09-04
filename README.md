# Action Monitor task

The second point of the task description is:

2. The program should allow two user to communicate between themselves pushing the messages to the destination user. All the messages should be stored in a database.

My understanding here is:   the core here is to create sort of a chat application allowing at least two people to message each other forth and back, and keep and present some stats about the chat.

So I have crafted such functionality, but to not reinvent the wheel, I have leveraged on the Spring Boot websocket starter guide, it just lays out the initial foundation on top of which I’ve built that sort of chat, using a H2 in memory database to store messages, names of the attendees of the chat, and roughly the app stats.  

So the main page of the solution, allows a user to connect and send messages to the chat, it shows as well who else is connected to the chat room.

The application can be accessed at localhost:8080

The H2 database is an in-memory database, it can be accessed via the H2 console at localhost:8080/h2-ui, the username is sa, there is no password.

The connection url is: jdbc:h2:mem:socketprof

As requested, the localhost:8080/action-monitor end point, show the stats of the current chat. The chat stats are cleaned each time the app is started.


# Architecture

This a Spring MVC application with a cannonical n-tiers MVC architecture, the packages structure is the  classic one enforcing separation of concerns.

I do like to separate the domain entities, and domain aggregates in a domain package. But, in this case, I’ve considered the app core to be the chat, and the monitorization is not main domain functionality, so the AppStats aggregate lives in the util package.

The representation resources, .html, .css and  .js files are Spring Boot mere resources, the reason for this is that as SpringBoot can be deployed as a fat jar file as well as a war file, there is not support for JSP.

The overall packages structure is: 

	com.monitor
		|_ api
		|_ config
		|_ db
		|_ domain
		|_ service
		|_ util



# Testing 

The websocket technology is a well stablished technology, nevertheless,  we leverage on the testing integration test developed by the Spring folks. This test asures the stomClients are working fine !

Regarding testing I am a believer on testing by significance, pursuing high coverage of testing is going against Paretto’s Law.  I believe on testing by significance, leveraging on transitive testing.

In order to complete this exercise, it's been tested the Integration between service and persistence layer, by testing all the three main Service components capability to save, delete and read.

We have used just Junit, there is no need for mocks for this simple exercise.



