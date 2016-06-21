# Ticket Booking System

####Ticket booking system has following main functions
* Get list of available seats optionally by seating levels
* Find and hold the best available seats on behalf of a customer, optionally selecting the min/max Levels
* Reserve and commit a specific group of held seats for a customer
* Search Booking by Customer Email

###Used Technologies

* Spring core for dependency injection
* Spring MVC for REST Implementation
* Hibernate for ORM
* Derby Database for Persistence
* Junit/EasyMock/SpringTest for unit testing
* Jquery/HTML/CSS/Javascript for front-end

###Configurations
* To configure time to expire booking and reservation code length following properties should be updated before deploying in application server
   File Name: \src\main\resources\properties\common.properties
	
	`booking.timeToExpireBooking=60
	booking.reservationCodeLength=10`

* To configure front-end endpointURLs (if required)
  change the following line in \src\main\webapp\WEB-INF\pages\index.jsp file.

	`var restAPIEndPoint="http://localhost:8080/TicketBooking/";`

#### To deploy project using maven embedded tomcat
---------------------------------------------------
* install java 1.8 and maven  command in your computer
* download zip file from https://github.com/rmsor/TicketBooking/archive/master.zip
* unzip file TicketBooking-master.zip
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
* unzip file TicketBooking-master.zip
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
* unzip file TicketBooking-master.zip
* import project into eclipse
* convert project to maven project
* run project in server (tomcat 7 version)
* open your browser and access application using following URL
	`http://localhost:8080/TicketBooking/`


### REST APIs

#####ServiceURL:: http://localhost:8080/TicketBooking
#####GET list of Levels:              {{ServiceURL}}/rest/level/list/v1_0 

Sample response

```json
{
  "levels": [
    {
      "levelId": 1,
      "levelName": "Orchestra 1",
      "price": 100,
      "noOfRows": 25,
      "seatsInRow": 50
    },
    {
      "levelId": 2,
      "levelName": "Main 2",
      "price": 75,
      "noOfRows": 20,
      "seatsInRow": 100
    },
    {
      "levelId": 3,
      "levelName": "Balcony 1",
      "price": 50,
      "noOfRows": 15,
      "seatsInRow": 100
    },
    {
      "levelId": 4,
      "levelName": "Balcony 2",
      "price": 40,
      "noOfRows": 15,
      "seatsInRow": 100
    }
  ]
}
```

#####GET available Seat:              {{ServiceURL}}/rest/booking/seatsavailable/v1_0
Sample resonse: no level send in reques
```json
{
  "availableSeats": 6000
}
```
Sample response: level passed in request
/rest/booking/seatsavailable/v1_0?level=4
```json
{
  "availableSeats": 1500,
  "level": "Balcony 2"
}
```	

#####POST Add booking:                {{ServiceURL}}/rest/booking/add/v1_0
Header: Content-Type: application/json

Sample request
```json
{
    "numSeats":"10",
	"minLevel":1,
    "maxLevel" :2,
    "customerEmail":"ram@gmail.com"
}
```
Sample Response
```json
{
  "bookingId": 3,
  "bookedDate": 1466532078443,
  "grandTotal": 1000,
  "bookingType": "BOOKED",
  "customer": {
    "customerId": 1,
    "email": "ram@gmail.com"
  },
  "bookingDetails": [
    {
      "level": {
        "levelId": 1,
        "levelName": "Orchestra 1",
        "price": 100,
        "noOfRows": 25,
        "seatsInRow": 50
      },
      "bookedSeats": 10,
      "totalPrice": 1000
    }
  ]
}
```

#####PUT Reserve booking:             {{ServiceURL}}/rest/booking/reserve/v1_0
Header: Content-Type: application/json

Sample request
```json
{
    "seatHoldId":1,
    "customerEmail":"ram@gmail.com"
}
```
Sample Response
```json
{
  "bookingId": 4,
  "bookedDate": 1466532164555,
  "grandTotal": 3750,
  "bookingType": "RESERVED",
  "customer": {
    "customerId": 1,
    "email": "ram@gmail.com"
  },
  "bookingDetails": [
    {
      "level": {
        "levelId": 2,
        "levelName": "Main 2",
        "price": 75,
        "noOfRows": 20,
        "seatsInRow": 100
      },
      "bookedSeats": 50,
      "totalPrice": 3750
    }
  ],
  "reservationCode": "57tvayBEGV"
}
```
#####GET List of booking by Customer: {{ServiceURL}}rest/booking/list/v1_0?customerEmail={emailAddress}
```json
{
  "bookings": [
    {
      "bookingId": 1,
      "bookedDate": 1466531986579,
      "grandTotal": 20000,
      "bookingType": "RESERVED",
      "customer": {
        "customerId": 1,
        "email": "ram@gmail.com"
      },
      "bookingDetails": [
        {
          "level": {
            "levelId": 1,
            "levelName": "Orchestra 1",
            "price": 100,
            "noOfRows": 25,
            "seatsInRow": 50
          },
          "bookedSeats": 200,
          "totalPrice": 20000
        }
      ],
      "reservationCode": "eiF7Q6txmF"
    },
    {
      "bookingId": 2,
      "bookedDate": 1466531996548,
      "expiredDate": 1466532056560,
      "grandTotal": 3750,
      "bookingType": "EXPIRED",
      "customer": {
        "customerId": 1,
        "email": "ram@gmail.com"
      },
      "bookingDetails": [
        {
          "level": {
            "levelId": 2,
            "levelName": "Main 2",
            "price": 75,
            "noOfRows": 20,
            "seatsInRow": 100
          },
          "bookedSeats": 50,
          "totalPrice": 3750
        }
      ]
    },
    {
      "bookingId": 3,
      "bookedDate": 1466532078443,
      "expiredDate": 1466532138457,
      "grandTotal": 1000,
      "bookingType": "EXPIRED",
      "customer": {
        "customerId": 1,
        "email": "ram@gmail.com"
      },
      "bookingDetails": [
        {
          "level": {
            "levelId": 1,
            "levelName": "Orchestra 1",
            "price": 100,
            "noOfRows": 25,
            "seatsInRow": 50
          },
          "bookedSeats": 10,
          "totalPrice": 1000
        }
      ]
    },
    {
      "bookingId": 4,
      "bookedDate": 1466532164555,
      "grandTotal": 3750,
      "bookingType": "RESERVED",
      "customer": {
        "customerId": 1,
        "email": "ram@gmail.com"
      },
      "bookingDetails": [
        {
          "level": {
            "levelId": 2,
            "levelName": "Main 2",
            "price": 75,
            "noOfRows": 20,
            "seatsInRow": 100
          },
          "bookedSeats": 50,
          "totalPrice": 3750
        }
      ],
      "reservationCode": "57tvayBEGV"
    }
  ]
}
```	