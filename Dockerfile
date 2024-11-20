FROM openjdk:17.0.2-slim-buster
ARG LIBS_PATH=./build/libs
ARG JAR_NAME=*.jar
COPY ${LIBS_PATH}/${JAR_NAME} /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]