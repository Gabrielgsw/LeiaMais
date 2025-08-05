FROM node:18-alpine as front

WORKDIR /frontend

COPY frontend/ /frontend/

RUN npm install && npm run build

FROM nginx

COPY --from=front /frontend/dist/ /usr/share/nginx/html