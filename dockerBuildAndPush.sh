docker rmi perssonalisation-service:latest
docker rmi vebstechbee03/tech-bee:perssonalisation-service-latest
docker build -t perssonalisation-service:latest .
docker tag perssonalisation-service:latest vebstechbee03/tech-bee:perssonalisation-service-latest
docker push vebstechbee03/tech-bee:perssonalisation-service-latest
