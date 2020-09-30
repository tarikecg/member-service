FROM openjdk:12-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# ENTRYPOINT ["java", "-classpath", "mysql-connector-java-5.1.6.jar:.","-jar","/app.jar"]