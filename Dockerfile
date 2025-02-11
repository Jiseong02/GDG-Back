FROM eclipse-temurin:21-jre-alpine
WORKDIR /GDG-Back
COPY /build/libs/gdgback-latest.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]