FROM node:18.12-alpine

WORKDIR /app

COPY . .

EXPOSE 8080

RUN npm install

CMD ["npm", "run", "serve"]