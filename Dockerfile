FROM eclipse-temurin:21-jre-alpine

# Google Cloud ADC를 위한 설치 및 인증 설정
ENV GOOGLE_APPLICATION_CREDENTIALS=/path/to/credentials.json

WORKDIR /GDG-Back
COPY /build/libs/gdgback-latest.jar /gdgback.jar
ENTRYPOINT ["java","-jar","/gdgback.jar"]