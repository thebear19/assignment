# Stage 1: Build the React app
FROM node:16 AS build
LABEL authors="CHATCHAWANN"

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . ./
RUN npm run build

# Stage 2: Serve the app with Nginx
FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html
COPY deployment/nginx.conf /etc/nginx/nginx.conf

EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]