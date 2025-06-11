FROM openjdk:17-jdk-slim
WORKDIR /drugstore
COPY target/drugstore-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "drugstore-0.0.1-SNAPSHOT.jar"]
