/* Project: hosting a golf club tournament using objects and databases through Docker and MySQL to create membership and model classes.
   Name: Cameron Beanland
   Date: March 25th, 2025
 */

package com.golfclub.golfclub_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.golfclub.golfclub_api")
public class GolfclubApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(GolfclubApiApplication.class, args);
	}
}

