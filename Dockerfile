FROM eclipse-temurin:17

LABEL maintainer="mragamaliga@gmail.com"

WORKDIR /app

COPY target/springBoot-blog-rest-api-0.0.1-SNAPSHOT.jar /app/springBoot-blog-rest-api.jar

ENTRYPOINT ["java","-jar","springBoot-blog-rest-api.jar"]