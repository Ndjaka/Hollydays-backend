# Pour ajouter mysql au container docker
docker run --name mysql_container -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql:latest

# Pour ajouter Minio au container
docker run -p 9000:9000 -p 9001:9001 minio/minio server /data --console-address ":9001"

# Pour ajouter le server de messagerie Mail dev au container
docker pull maildev/maildev
docker run -p 1080:1080 -p 1025:1025 maildev/maildev