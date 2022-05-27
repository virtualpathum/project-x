FROM java:8-jdk-alpine
LABEL maintainer="virtualpathum@gmail.com"
VOLUME /tmp
EXPOSE 8090
COPY ./target/project-x-1.0.0.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch project-x-1.0.0.jar'
ENTRYPOINT ["java","-jar","project-x-1.0.0.jar"]