.DEFAULT_GOAL := docker

clean:
	mvn clean

build: clean
	mvn build

run: clean
	mvn quarkus:dev

docker:
	docker-compose -f src/main/docker/docker-compose.yml up

receive:
	kafkacat -b localhost -G single todo

send:
	echo "{\"description\":\"string\",\"done\":true,\"due\":\"2021-02-14\",\"start\":\"2021-02-14\",\"title\":\"string\"}%" | kafkacat -b localhost -t todo-in -K%
