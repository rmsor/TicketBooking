# ticket-booking
## Ticket Booking System


#### To deploy project using maven embedded tomcat
---------------------------------------------------
* install java 1.8 and maven  command in your computer
* download zip file from https://github.com/rmsor/TicketBooking/archive/master.zip
* unzip folder TicketBooking-master.zip
* go to terminal and cd to extracted location
* type following command
	`mvn clean install tomcat7:run`
* project will be deployed in tomcat
* open your browser and access application using following URL
	`http://localhost:8080/TicketBooking/`
	

#### To deploy project using standalone tomcat
---------------------------------------------------
* install java 1.8 and maven command in your computer
* download zip file from https://github.com/rmsor/TicketBooking/archive/master.zip
* unzip folder TicketBooking-master.zip
* go to terminal and cd to extracted location
* type following command
	`mvn package`
* project will be deployed as "TicketBooking.war" in target directory in extracted location
* copy "TicketBooking.war" file to webapps folder of tomcat installation directory.
* start tomcat server using startup.bat/startup.sh scripts
* open your browser and access application using following URL
	`http://localhost:8080/TicketBooking/`
	
#### To deploy project using eclipse
---------------------------------------------------
* install java 1.8 in your computer
* download zip file from https://github.com/rmsor/TicketBooking/archive/master.zip
* unzip folder TicketBooking-master.zip
* import project into eclipse
* convert project to maven project
* run project in server (tomcat 7 version)
* open your browser and access application using following URL
	`http://localhost:8080/TicketBooking/`


## REST APIs

##ServiceURL:: http://localhost:8080/TicketBooking

###GET list of Levels:              {{ServiceURL}}/rest/level/list/v1_0
###GET available Seat:              {{ServiceURL}}/rest/booking/seatsavailable/v1_0
###POST Add booking:                {{ServiceURL}}/rest/booking/add/v1_0
###PUT Reserve booking:             {{ServiceURL}}/rest/booking/reserve/v1_0
###GET List of booking by Customer: {{ServiceURL}}rest/booking/list/v1_0


	