# Node.js 이미지를 사용해 Vue.js 애플리케이션 빌드
FROM node:16-alpine3.11 AS build

WORKDIR /front

# package.json 및 package-lock.json만 복사
COPY package*.json ./

# 의존성 설치
RUN npm install

# 소스 코드 복사
COPY . .

# 빌드 실행
RUN npm run build

# Nginx로 배포할 빌드 파일을 복사
FROM nginx:1.26.2
COPY --from=build /front/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
