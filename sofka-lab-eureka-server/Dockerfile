FROM amazoncorretto:17-alpine-jdk
RUN apk --no-cache --upgrade add curl
VOLUME /tmp
ARG JAR_FILE
ARG PORT

COPY ${JAR_FILE} app.jar

EXPOSE ${PORT}
ENTRYPOINT ["java", "-Duser.timezone=America/Bogota", "-jar","app.jar"]