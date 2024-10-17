Steps to run the app:- 

1. Run the RabbitMQ Application via docker (Run Dcoker desktop & execute below command from root:- 
 `docker-compose -f deployment/docker-compose/infra.yml up -d`

2. Run the Springboot application (RabbitMQDemoApplication) `./mvnw spring-boot:run`
3. Send messages to Rabbit MQ using postman
4. Request Format (URL `http://localhost:8080/send`) --
```
    /*Body:-
    {
    "routingKey" :"new-message-queue",
    "payload": {
        "content" : "Hi sambit"
    }
    }
     */
  ```
5. Rabbit MQ URL :- `http://localhost:15672/` , Username:- `guest` , Password:- `guest`