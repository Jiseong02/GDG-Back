FROM eclipse-temurin:21-jre-alpine
COPY GDG-Back/build/libs/gdgback-latest.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]