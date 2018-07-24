SHELL := /bin/bash

# Builders

build:
	clear && \
	./gradlew clean && \
	./gradlew build && \
	sudo docker-compose up --build --force-recreate --remove-orphans

build-in-docker:
	docker-compose up -f ./docker-compose-build.yml --build

# Standalone Runners

run_service_discovery:
	clear && ./gradlew :service_discovery:bootRun

run_config_server:
	clear && ./gradlew :config_server:bootRun

run_rabbitmq:
	clear && docker run -d --hostname cs-rabbit --network host --name cs-rabbit rabbitmq:3-management

run_mongodb:
	clear && docker run -d --network host --name cs-mongo mongo

# Tests
# Test all with Docker
test:
	clear && docker-compose -f ./docker-compose-test.yml up --build --remove-orphans

.PHONY: build
