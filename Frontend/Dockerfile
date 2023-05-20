FROM node:18.12-alpine

WORKDIR /app

COPY . .

EXPOSE 8080

RUN nmp install

CMD ["npm", "run", "serve"]