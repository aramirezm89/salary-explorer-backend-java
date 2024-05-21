FROM gradle:jdk17
LABEL authors="aramirezm"
EXPOSE 8080

WORKDIR /app

COPY build/libs/*.jar /app/salary-explorer.jar

ENTRYPOINT ["java", "-jar", "/app/salary-explorer.jar"]
