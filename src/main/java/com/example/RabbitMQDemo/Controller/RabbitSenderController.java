package com.example.RabbitMQDemo.Controller;

import com.example.RabbitMQDemo.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitSenderController {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    public RabbitSenderController(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }

    /* Request Format --
    //http://localhost:8080/send

    Body:-
    {
    "routingKey" :"new-message-queue",
    "payload": {
        "content" : "Hi sambit"
    }
    }
     */
    @PostMapping("/send")
    public void sendMessage(@RequestBody MyMessage myMessage)
    {
        System.out.println(myMessage);
        rabbitTemplate.convertAndSend(applicationProperties.eventsExchange(),myMessage.routingKey(),myMessage.payload());
    }

    public record  MyMessage(String routingKey,MyPayload payload){};
    public record MyPayload(String content) {};


}
