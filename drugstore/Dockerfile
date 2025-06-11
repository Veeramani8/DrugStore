FROM openjdk:17-jdk-slim
WORKDIR /drugstore
COPY target/drugstore-0.0.1-SNAPSHOT.jar .
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "drugstore-0.0.1-SNAPSHOT.jar"]
