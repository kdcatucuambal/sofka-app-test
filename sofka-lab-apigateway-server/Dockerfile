FROM amazoncorretto:17-alpine-jdk
RUN apk --no-cache --upgrade add curl
VOLUME /tmp
ARG JAR_FILE
ARG PORT
ARG HOST_EUREKA_SERVER

ENV HOST_EUREKA_SERVER=${HOST_EUREKA_SERVER}

COPY ${JAR_FILE} app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java", "-Duser.timezone=America/Bogota", "-jar","app.jar"]