# 基础镜像
FROM openjdk:17-jdk-slim

# 在容器中创建应用目录
WORKDIR /app

# 复制 Spring Boot 可执行 JAR 文件到容器中
COPY target/*.jar app.jar

# 暴露应用端口
EXPOSE 7860

# 运行 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]
