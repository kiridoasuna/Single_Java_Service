# 使用官方的 Maven 镜像作为构建阶段的基础镜像
FROM maven:3.8 AS build
WORKDIR /app

# 将 pom.xml 和源代码复制到镜像中
COPY pom.xml .
COPY src ./src

# 构建应用并创建可执行 JAR 文件
RUN mvn clean package -DskipTests

# 使用官方的 OpenJDK 镆像作为运行阶段的基础镜像
FROM openjdk:17-jdk-slim
WORKDIR /app

# 从构建阶段复制构建好的 JAR 文件到运行阶段
COPY --from=build /app/target/usermicroservices-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用监听的端口
EXPOSE 8888

# 定义启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]