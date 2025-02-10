# 공황 및 불안 장애 대처 서비스 제공 애플리케이션

## 목표
- 공황에 대처할 수 있는 AI 에이전트 학습
- 에이전트와 사용자를 연결할 수 있는 네트워크 구성
- 사용자 데이터를 저장할 수 있는 DB 구성
- 사용자 데이터를 분석할 수 있는 환경 구성
   
## AI Agent
공황을 겪는 사용자 전문 음성 상담 AI 에이전트

요구사항:
- 맥락 분석
   - 공황 원인
   - 사용자 감정 상태
   - 상황 심각성
- 의사소통:
   - 실시간 음성 인식
   - 음성 출력
- 데이터 학습:
  - 사용자와의 대화를 기록
  - 기록한 데이터에 사용자 정보 마스킹 적용
  - 마스킹 된 대화 데이터를 학습 데이터로 활용

## Database
사용자 데이터 CRUD를 위한 데이터베이스
요구사항:
- 사용자 데이터
- 대화 로그
DBMS:
- MongoDB
Cloud Service:
- MongoDB Atlas
  
## Networking
서비스 간의 네트워크 연결
요구사항:
- 백엔드 서버와 DB 서버 연결
- 백엔드 서버와 Gemini API 서버 요청망 구성
- 프론트 서버와 백엔드 서버 연결

## TODO
1. 파인튜닝을 위한 학습/검증/테스트 데이터셋 구성 => 패스
2. 사용자 정보 및 대화 로그 저장, 조회를 위한 데이터베이스 구성 
3. Gemini API 모델과 원활히 통신할 수 있는 백엔드 환경 구성
4. 


# 백엔드 서비스 구현에 사용되는 프레임워크/ 클라우드 서비스
- REST API - Spring Boot
- Gemini Cloud - Google Cloud
- Database Cloud - MongoDB Atlas
- Deployment - Docker
- CI/CD - Github Actions
  
![image](https://github.com/user-attachments/assets/6343e187-213b-47cc-8cf1-872098406a8c)
