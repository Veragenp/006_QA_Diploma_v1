FROM node:12-alpine3.9
WORKDIR opt/app
RUN npm install
COPY . .
EXPOSE 9999
CMD [ "node", "server.js" ]