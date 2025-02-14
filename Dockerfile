FROM eclipse-temurin:21-jre-alpine

# Google Cloud ADC를 위한 설치 및 인증 설정
RUN echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | tee -a /etc/apt/sources.list.d/google-cloud-sdk.list && curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | gpg --dearmor -o /usr/share/keyrings/cloud.google.gpg && apt-get update -y && apt-get install google-cloud-cli -y
ENV GOOGLE_APPLICATION_CREDENTIALS=/path/to/credentials.json

WORKDIR /GDG-Back
COPY /build/libs/gdgback-latest.jar /gdgback.jar
ENTRYPOINT ["java","-jar","/gdgback.jar"]