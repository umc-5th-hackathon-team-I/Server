FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} handy.jar
ENTRYPOINT ["java","-jar","/handy.jar"]
