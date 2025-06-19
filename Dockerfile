FROM amazoncorretto:24-alpine-jdk
ARG JAR_FILE=target/mars-rover-exercise-1.0.0.jar
COPY ${JAR_FILE} mars-rover-exercise.jar
CMD [ "java", "-jar", "/mars-rover-exercise.jar" ]
EXPOSE 8080/tcp