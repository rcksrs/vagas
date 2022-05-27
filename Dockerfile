FROM adoptopenjdk/openjdk8:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} vagas.jar
ENTRYPOINT ["java","-jar","vagas.jar"]