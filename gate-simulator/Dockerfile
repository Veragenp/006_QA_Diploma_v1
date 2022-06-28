FROM node:8.16.2-alpine
WORKDIR opt/app
RUN npm install
COPY . .
EXPOSE 9999
CMD [ "node", "server.js" ]