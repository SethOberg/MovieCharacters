FROM maven:3-openjdk-17 AS maven
EXPOSE 8080
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17 as runtime
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=/app/target/*.jar
COPY --from=maven ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]