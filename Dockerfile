FROM gradle:8-jdk17 AS build

WORKDIR /kafka-consumer

COPY build.gradle settings.gradle gradlew gradlew.bat /kafka-consumer/
COPY gradle /kafka-consumer/gradle

COPY src /kafka-consumer/src

RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /kafka-consumer

COPY --from=build /kafka-consumer/build/libs/consumer-demo-1.0.0.jar /kafka-consumer/consumer-demo-1.0.0.jar

EXPOSE 8882

CMD ["java", "-jar", "consumer-demo-1.0.0.jar"]