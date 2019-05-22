# Alpine because it's lighter
FROM openjdk:8-jdk-alpine

# Set ENV variables
ENV PORT=8081
ENV CONFIG_URL="http://config:8888"
ENV EUREKA_URL="http://eureka:8761"

# Add JAR file and run it as entrypoint
ADD target/suggestion-server.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8081