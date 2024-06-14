FROM node:22 AS ngbuild

WORKDIR /frontend

# Install angular 
RUN npm i -g @angular/cli@17.3.8

COPY frontend/angular.json .
COPY frontend/package*.json .
COPY frontend/tsconfig*.json .
COPY frontend/src src

RUN npm ci 
RUN ng build

FROM openjdk:21 AS javabuild

WORKDIR /webcamapp

COPY backend/mvnw .
COPY backend/pom.xml .
COPY backend/.mvn .mvn
COPY backend/src src

COPY --from=ngbuild /frontend/dist/frontend/browser/ src/main/resources/static

RUN ./mvnw package -Dmaven.test.skip=true

FROM openjdk:21 

WORKDIR /app

COPY --from=javabuild /webcamapp/target/backend-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080 

EXPOSE ${PORT}
ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar