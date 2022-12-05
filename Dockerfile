FROM maven:latest AS build-project
ADD . ./spring-demo-master
WORKDIR /spring-demo-master
RUN mvn clean install


FROM openjdk:11.0.6-jre
EXPOSE 8080

COPY --from=build-project /spring-demo-master/target/ds-2020-0.0.1-SNAPSHOT.jar ./spring-demo-master.jar
CMD ["java", "-jar", "spring-demo-master.jar"]

