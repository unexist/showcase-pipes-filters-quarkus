define JSON_TODO_QUARKUS
curl -X 'POST' \
  'http://localhost:8080/todo' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "description": "string",
  "done": true,
  "dueDate": {
    "due": "2021-05-07",
    "start": "2021-05-07"
  },
  "title": "string"
}'
endef
export JSON_TODO_QUARKUS

define TODO_KAFKA
echo 'test%{
  "description": "string",
  "done": false,
  "dueDate": {
    "due": "2022-05-08",
    "start": "2022-05-07"
  },
  "title": "string"
}' | kafkacat -t todo_in -b localhost:$(RPK_PORT) -P -K%
endef
export TODO_KAFKA

# Docker
.PHONY: docker
docker:
	docker-compose -f src/main/docker/docker-compose.yml -p pipesfilters up

# Tools
todo-quarkus:
	@echo $$JSON_TODO_QUARKUS | bash

list:
	@curl -X 'GET' 'http://localhost:8080/todo' -H 'accept: */*' | jq .

# RPK
rpk-port:
	$(eval RPK_PORT := $(shell docker inspect --format='{{(index (index .NetworkSettings.Ports "9092/tcp") 0).HostPort}}' $(shell docker ps --format "{{.ID}}" --filter="ancestor=vectorized/redpanda:v21.5.5")))

# Kafkacat
kat-send: rpk-port
	@echo $$TODO_KAFKA | bash

kat-listen-in: rpk-port
	kafkacat -t todo_in -b localhost:$(RPK_PORT) -C

kat-listen-out: rpk-port
	kafkacat -t todo_out -b localhost:$(RPK_PORT) -C

kat-test: rpk-port
	kafkacat -t todo_in -b localhost:$(RPK_PORT) -P
