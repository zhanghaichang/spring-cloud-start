FROM frolvlad/alpine-oraclejdk8:slim
VOLUME ["~/data/logs/","/data/logs/"]
ADD target/spring-cloud-eureka-0.0.1.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]