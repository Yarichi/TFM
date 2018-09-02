#!/bin/bash

sudo kubectl create -f deployment.yml

sudo kubectl create -f app-service.yml
sudo kubectl create -f web-service.yml
sudo kubectl create -f routines-service.yml
sudo kubectl create -f location-service.yml
sudo kubectl create -f frontend-service.yml
sudo kubectl create -f bot-service.yml


sudo kubectl create -f db-app-service.yml
sudo kubectl create -f db-web-service.yml
sudo kubectl create -f db-routines-service.yml
sudo kubectl create -f db-location-service.yml

sudo kubectl create -f ingress.yaml
