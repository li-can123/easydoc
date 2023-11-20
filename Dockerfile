FROM openjdk:8-jdk-alpine
EXPOSE 8082
ARG JAR_FILE=target/easydoc-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]