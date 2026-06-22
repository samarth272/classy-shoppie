FROM maven:3.9-eclipse-temurin-21 AS build
#build stage & runtime stage
WORKDIR /app

COPY pom.xml .
#suppose you added new dependencies, but docker using previous pom => so above command required for every build
COPY src ./src
#copy source code
RUN mvn clean package -DskipTests
#created jar here

#below is runtime stage
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/product-service.jar app.jar
#copy jar from build stage not runtime stage
#EXPOSE 8081
#command for debug below with docker
EXPOSE 8081 5005

#ENTRYPOINT ["java","-jar","app.jar"]
ENTRYPOINT [ "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","app.jar"]