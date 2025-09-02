FROM eclipse-temurin:21-jre

# 애플리케이션 실행 디렉토리
WORKDIR /app

#  CI에서 만든 JAR 파일의 경로를 'moamoa' 모듈 경로로 변경
COPY moamoa/build/libs/*.jar /app/app.jar

# 실행
ENTRYPOINT ["java","-jar","/app/app.jar"]
