FROM gradle:8.5-jdk11-jammy
WORKDIR /app
COPY . .

RUN mkdir -p /home/videos/dramas
RUN mkdir -p /home/videos/scenes
RUN mkdir -p /home/videos/chunks

RUN mkdir -p /home/image

RUN chmod +x ./gradlew
RUN gradle bootJar


ENTRYPOINT ["java", "-jar", "/app/build/libs/app.jar"]
EXPOSE 8081