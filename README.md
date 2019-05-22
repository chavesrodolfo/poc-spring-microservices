# POC Microservices

Micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is between 15 and 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service is a worldwide success, prepared to be fault tolerant, responsive and resilient.

## Overview

The architecture is composed by four services:

- `config-server`: All of system configurations files are in the repository `https://github.com/chavesrodolfo/configs-repo` and **Config Server** is responsible for reading the information in the repository and providing it to applications through HTTP requests.
- `eureka-server`: Service Discovery Server created with **Eureka**
- `zuul-server`: API Gateway created with **Zuul** that uses the `eureka-service` to send the requests to the services. It uses **Ribbon** as Load Balancer
- `suggestion-server`: Simple REST service created with **Spring Boot** to suggest tracks based on location.

## How to use

To test this architecture you need to have: **JDK 8+**, **Docker** and **Maven** installed

- Clone this repo and enter it

- Run the `start.sh` script, it uses **Maven** to build the `.jar` file for each service and then use Docker to build the containers with the `.jar` files

In the default configuration you will have:

- **Config Server** running on port `8888`
- **Eureka Server** running on port `8761`, access `http://localhost:8761` to see the dashboard
- **Zuul Server** running on port `8080`
- **Suggestion Server** running on port: `8081`, you will send the requests to this service

After running the containers, head to `http://localhost:8761` to make sure that the service (**Suggestion Server**) are registered in the **Eureka Server**, when they're all registered you can test the application using swagger through the following link: `http://localhost:8081/swagger-ui.html`