#!/bin/bash

echo Config Server: BUILDING
cd config-server
mvn clean install -U
cd ..
clear

echo Config Server: BUILD DONE
echo Eureka Server: BUILDING
cd eureka-server
mvn clean install -U
cd ..
clear

echo Config Server: BUILD DONE
echo Eureka Server: BUILD DONE
echo Zuul Server: BUILDING
cd zuul-server
mvn clean install -U
cd ..
clear

echo Config Server: BUILD DONE
echo Eureka Server: BUILD DONE
echo Zuul Server: BUILD DONE
echo Suggestion Server: BUILDING
cd suggestion-server
mvn clean install -U
cd ..
clear

echo Config Server: BUILD DONE
echo Eureka Server: BUILD DONE
echo Zuul Server: BUILD DONE
echo Suggestion Server: BUILD DONE
echo Jaimail Router Server: BUILDING
cd jaimail-router-server
mvn clean install -U
cd ..
clear

echo Config Server: BUILD DONE
echo Eureka Server: BUILD DONE
echo Zuul Server: BUILD DONE
echo Suggestion Server: BUILD DONE
echo Jaimail Router Server: BUILD DONE
echo ---
echo Starting Application...
docker-compose up -d --build

sleep 60