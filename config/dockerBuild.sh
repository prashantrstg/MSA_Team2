docker run --name discovery-service -d -p 8761:8761 574a127366bc
docker run --name config-service -d -p 8081:8081 -v /home/ubuntu/meru_integration/meru_config:/app/config e105a550a38c
docker run --name product-view-service -d -p 8083:8083 fbba095f75b1
docker run --name product-service -d -p 8010:8010 ccb34f6f139c
docker run --name price-service -d -p 9191:9191 9103deb38c8e
docker run --name inventory-service -d -p 9090:9090 a9e010200a56
docker run --name promotion-service -d -p 9299:9299 3096a0cae82
docker run --name gateway-service -d -p 8080:8080 c4996fb14c4a
