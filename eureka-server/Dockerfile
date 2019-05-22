# Alpine because it's lighter
FROM openjdk:8-jdk-alpine

# Set ENV variables
ENV PORT=8761
ENV CONFIG_URL="http://config:8888"

# Add JAR file and run it as entrypoint
ADD target/eureka-server.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8761