FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/ds-2020-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
