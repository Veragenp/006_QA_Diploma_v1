FROM node:16
WORKDIR /usr/src/app
RUN npm install
COPY . .
EXPOSE 9999
CMD [ "node", "server.js" ]