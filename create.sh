#!/bin/bash

sudo docker build -t "tfm-app" ./microservice-app
sudo docker build -t "tfm-web" ./microservice-web
sudo docker build -t "tfm-routines" ./microservice-routine
sudo docker build -t "tfm-location" ./microservice-geoposition
sudo docker build -t "tfm-frontend" ./node
sudo docker build -t "tfm-bot" ./bot
sudo docker build -t "tfm-db-app" -f ./Dockerfile-DB/Dockerfile-db-app ./Dockerfile-DB
sudo docker build -t "tfm-db-web" -f ./Dockerfile-DB/Dockerfile-db-web ./Dockerfile-DB
sudo docker build -t "tfm-db-routines" -f ./Dockerfile-DB/Dockerfile-db-routines ./Dockerfile-DB
sudo docker build -t "tfm-db-location" -f ./Dockerfile-DB/Dockerfile-db-location ./Dockerfile-DB

sudo docker tag tfm-app yarichi/$1:tfm-app
sudo docker tag tfm-web yarichi/$1:tfm-web
sudo docker tag tfm-routines yarichi/$1:tfm-routines
sudo docker tag tfm-location yarichi/$1:tfm-location
sudo docker tag tfm-frontend yarichi/$1:tfm-frontend
sudo docker tag tfm-bot yarichi/$1:tfm-bot
sudo docker tag tfm-db-app yarichi/$1:tfm-db-app
sudo docker tag tfm-db-web yarichi/$1:tfm-db-web
sudo docker tag tfm-db-location yarichi/$1:tfm-db-location
sudo docker tag tfm-db-routines yarichi/$1:tfm-db-routines

sudo docker push yarichi/$1
