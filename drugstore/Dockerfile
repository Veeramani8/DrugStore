FROM openjdk:17-jdk-alpine
WORKDIR /drugstore
COPY target/drugstore-0.0.1-SNAPSHOT.jar drugstore.jar
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "drugstore.jar"]
