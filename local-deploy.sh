mvn clean install

docker build -t lunch:local-latest .

docker compose -f ./docker-compose.yaml up

