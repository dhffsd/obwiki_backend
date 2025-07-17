# 基础镜像
FROM eclipse-temurin:11
# 作者
MAINTAINER xp
# 工作目录
WORKDIR /usr/local/java
# 同步docker内部的时间
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 设置时区
ENV TZ=Asia/Shanghai
EXPOSE 8880
# 复制jar包到/user/local/java下
ARG JAR_FILE
ADD ${JAR_FILE} ./obwiki.jar

ENTRYPOINT ["nohup","java","-Dspring.config.location=/usr/local/java/application.properties","-jar","/usr/local/java/obwiki.jar",">","/usr/local/java/obwiki.log","&>","&"]
