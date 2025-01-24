FROM openjdk:17-jdk

COPY build/libs/*SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=deploy

ENTRYPOINT ["java", "-jar", "/app.jar"]