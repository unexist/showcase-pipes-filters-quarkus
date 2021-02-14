.DEFAULT_GOAL := docker

clean:
	mvn clean

build: clean
	mvn build

run: clean
	mvn quarkus:dev

docker:
	docker-compose -f src/main/docker/docker-compose.yml up
