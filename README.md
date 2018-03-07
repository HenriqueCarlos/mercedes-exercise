# Mercedes-exercise
My solution to the Mercedes challenge on SINFO

My application was done using Java 9, Maven and Spring Framework, on a Linux environment. So you need those installed to build, test and run my application.

To build run the following command:
mvn package

To run the application run the following command:
mvn spring-boot:run

To test the application run the following command:
mvn test

# The API build has the following endpoints:

GET: /listByModel -> returns a JSON with the models available and the respective atributes (fuel and transmission)


GET: /listByFuel -> returns a JSON with the fuels available and the respective atributes (model and transmission)

GET: /listByTransmission -> returns a JSON with the transmission available and the respective atributes (model and fuel)

GET: /listByDealer -> returns a JSON with the name of the dealers available and the respective available vehicles

GET: /findClosestDealer -> returns the closest dealer to the user with the requested model, fuel and transmission.
this endpoint requires 5 parameters:
latitude -> the latitude of the user's current position
longitude -> the longitude of the user's current position
model -> the model of the wanted vehicle
transmission -> the transmission of the wanted vehicle
fuel -> the fuel of the wanted vehicle
An example of usage can be found below

http://localhost:8080/findClosestDealer?latitude=38.736574&longitude=-9.139184&model=AMG&fuel=ELECTRIC&transmission=AUTO

GET: /createBooking -> creates a booking and returns a String with a message about the successfulness of the operation
This endpoint requires 4 parameters:
vehicleId -> the id of the intended vehicle 
firstName -> the first name of the user
lastName -> the last name of the user
pickupDate -> the intended date in the format "Year-Month-Day'T'Hours-Mins-Seconds"
An example of usage can be found below:

http://localhost:8080/createBooking?vehicleId=768a73af-4336-41c8-b1bd-76bd700378ce&firstName=Test&lastName=Test&pickupDate=2018-03-05T10:30:00

GET: /cancelBooking -> cancels a booking and returns a String with a message about the successfulness of the operation
This endpoint requires 2 parameters:
id -> the id of the booking intended to be canceled
reason-> a text about the reason to cancel the booking
An example of usage can be found below:

http://localhost:8080/cancelBooking?id=ca7927a9-1262-4085-aad0-d06e105acd3a&reason=testText

# Implementation details

The application was designed taking in consideration the overall performance of the operations.
In terms of performance the execution speed was preferred over the memory usage, this means that when possible I haven choosen to use data structures that are more expensive in memory usage but faster in operation execution speed.

For example to store the bookings instead of a list or other simpler data struture I used 2 hashMaps. The two hashMaps work together to ensure a optimized performence in terms of speed sacrificing some memory use.
The 2 hashMaps work in the following way:
-> first a class called Index was created that stores 2 values.
-> the first hashMap associates the id of a booking to a Index that has the vehicle Id and the pickup date, this way I can associate (UniqueID : <vehicleID, pickupDate>)
-> the second hashMap associates the Index with a Booking (<vehicleID, pickupDate> : Booking)
These hashMaps are specially eficcient when the create or cancel booking operation are called, since the need to check the existence of a already booked vehicle or a booking Id can be O(N) using a List. With my solution is done in O(1) in the most cases.

The find Dealer runs in O(N) since I use a auxiliary variable that stores the current closest dealer at the Dealer List iteration, that is updated at every position of the List.

The list by dealer also runs in O(N) since the dealers are stored in a List and it only needs to iterate once to find all the dealers.

The list by model, fuel and transmission run in O(N+NlogN) since that in the application boot I create a List of the vehicles, sacrificing the memory usage but having a better speed operation. With this list I don't need to iterate to the dealers list every time I need to list the vehicles. It's O(N+NlogN) since that I sort the list by model, fuel, or transmission which is O(NlogN) and then I iterate once the full list O(N).

# Improvements

I didn't implement the suggested improvements, since I didn't had the time to do it.
Besides those improvements I believe that a more extensive Error handling could be done in my Application, and also a more extensive set of tests.

I hope that everything is clear. If you have any questions don't hesitate to contact me.

Henrique Carlos, 2018







