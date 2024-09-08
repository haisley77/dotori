FROM node:16-alpine3.11

WORKDIR /front

# package.json 및 package-lock.json 복사
COPY package*.json ./

# 의존성 설치
RUN npm install

# 애플리케이션 소스 코드 복사
COPY . .

CMD ["npm","run","build"]
EXPOSE 3000
