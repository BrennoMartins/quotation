# Etapa de construção
FROM openjdk:17-jdk-slim as builder
WORKDIR /app
COPY . .
#RUN ./gradlew build -x test

# Etapa de execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8084

# Entrypoint para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
