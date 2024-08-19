FROM node:16-alpine3.11 as build-stage

WORKDIR /front

RUN npm run build # React 애플리케이션 빌드

FROM nginx:latest as production-stage

# 빌드 단계에서 생성된 빌드 파일을 Nginx의 서빙 디렉토리로 복사
COPY --from=build-stage /front/build /usr/share/nginx/html

# 기본 포트로 80을 노출
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
