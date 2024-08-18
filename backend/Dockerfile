FROM docker
COPY --from=docker/buildx-bin:latest /buildx /usr/libexec/docker/cli-plugins/docker-buildx

#RUN mkdir -p /home/videos/dramas
#RUN mkdir -p /home/videos/scenes
#RUN mkdir -p /home/videos/chunks

#RUN mkdir -p /home/image

FROM openjdk:11-jdk
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/build/libs/backend-0.0.1-SNAPSHOT.jar"]
