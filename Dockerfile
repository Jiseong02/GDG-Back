FROM eclipse-temurin:21-jre-alpine
WORKDIR /GDG-Back
COPY /build/libs/gdgback-latest.jar /gdgback.jar
ENTRYPOINT ["java","-jar","/gdgback.jar"]