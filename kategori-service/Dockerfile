FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD

MAINTAINER Ade Hendini
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar"]