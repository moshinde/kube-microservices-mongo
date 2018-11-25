# microservices-mongo

There are two microservcies user-service with user data and stock symbol and the other microservice stock service contains stock data 

1. Create namepsaces for database and other microservices

kubectl create namespace db-namespace
kubectl create namespace user-app-namespace
kubectl create namespace stock-app-label

2. Create mongo db pod in db-namespace(Refer mongo-pod.yml)

kubectl create -f mongo-pod.yml

3. Create user microservice deployment in user-app-namespace (refer user-service/user-deployment.yml)

kubectl create -f user-deployment.yml

4. Create stock microservice deployment in stock-app-namespace (refer stock-service/stock-deployment.yml)

kubectl create -f user-deployment.yml
