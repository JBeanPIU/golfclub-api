Okay! So despite Postman not working with this project, I feel I've got a decent enough idea on how the supported search API's *should* work.

Firstly, the API's provide functionality to help search for members or tournaments in the golfclub database, and one way to find it is by using http://localhost:8080/api/members/search. 
Using this and processing as a GET to Postman and searching via memName, memPhone, or memStartDate (member name, member phone, member start date) should be able to process the 
information and return any given info needed. My project has MySQL implementation, and you can easily connect it to port 8080 using mvn clean install, then running mvn spring-boot:run.

The issue that comes up is that Postman has issues with connecting, there was an issue with the Spring Boot mapping, but I thought I had it setup correctly.

If you'd like to run this program and try it, you'll have to:

- Clone the Git into a programming language, I used IntelliJ for this
- After cloning, go to terminal and type: mvn clean install
- After the project builds, type: mvn spring-boot:run

If done correctly, you should be able to reach http://localhost:8080 and start running queries (Must have MySQL running with golfclub_db)

When creating the database, keep it on port 3306, and replace any info required in application.properties with your own username and password.
