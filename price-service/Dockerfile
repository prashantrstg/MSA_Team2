FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/prashantrstg/MSA_Team2.git
FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/MSA_Team2 /app
WORKDIR "price-service"
RUN mvn install -DskipTests
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/price-service/target/pricing-meru-v1_0.jar /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","pricing-meru-v1_0.jar"]
