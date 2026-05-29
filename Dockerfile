FROM eclipse-temurin:25-jdk
LABEL authors="Rafael Ribeiro"

WORKDIR /app

COPY target/coupon-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
