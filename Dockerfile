FROM eclipse-temurin:11-jdk-focal

WORKDIR /app
COPY target/damovie.jar damovie.jar
ENTRYPOINT ["java","-jar","damovie.jar"]
EXPOSE 8000