FROM node:16-alpine3.11

WORKDIR /app

# package*.json을 app으로 복사
COPY package*.json ./

RUN npm install
RUN npm i -g @quasar/cli

# 현재 디렉토리의 모든 파일을 app으로 복사
COPY . .

# 프로젝트 빌드 위해 package.json에 정의된 빌드 스크립트 실행
RUN npm run build

CMD [ "npm", "run", "dev"]
EXPOSE 3000


