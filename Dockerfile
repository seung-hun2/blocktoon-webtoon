FROM openjdk:17-jdk-slim
COPY build/libs/webtoon-service-0.0.1-SNAPSHOT.jar webtoon-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/webtoon-service.jar"]
