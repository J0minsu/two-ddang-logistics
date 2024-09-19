# build 에서 사용할 이미지
FROM gradle:8.10.1-jdk17 AS build

WORKDIR /app

ARG FILE_DIRECTORY

COPY / /app

RUN ./gradlew core:compileJava delivery:bootjar

# 실제 컨테이너로 만들 이미지 베이스
FROM openjdk:17-jdk-slim

# build 단계로부터 파일을 가져올 수 있음!
# AS build로 선언해놨기에 --from=build!
COPY --from=build /app/delivery/build/libs/*SNAPSHOT.jar /delivery.jar

CMD ["java", "-jar", "delivery.jar"]
