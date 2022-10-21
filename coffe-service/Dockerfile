# Base image
#FROM openjdk:17-jdk-alpine
FROM amazoncorretto:17

# Create a volume pointing to /tmp
VOLUME /tmp

# Create a directory for newrelic
RUN mkdir /newrelic

# Define arguments that can be passed at build-time
ARG JAR_FILE
ARG NEW_RELIC_SRC_AGENT_FILE
ARG NEW_RELIC_SRC_CONFIG_FILE

# Define environment variables
ENV NEW_RELIC_TARGET_AGENT_FILE="/newrelic/newrelic.jar"
ENV NEW_RELIC_TARGET_CONFIG_FILE="/newrelic/newrelic.yml"
ENV ENV="dev"
ENV JAVA_OPTS="${JAVA_OPTS} -javaagent:${NEW_RELIC_TARGET_AGENT_FILE} -Dnewrelic.environment=${ENV}"

# Copy the src file and add it to the filesystem of the container
COPY ${NEW_RELIC_SRC_AGENT_FILE} ${NEW_RELIC_TARGET_AGENT_FILE}
COPY ${NEW_RELIC_SRC_CONFIG_FILE} ${NEW_RELIC_TARGET_CONFIG_FILE}
COPY ${JAR_FILE} app.jar

# Run the application with the commands
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar