package com.example.RabbitMQDemo.service;

import com.example.RabbitMQDemo.Controller.RabbitSenderController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {
    @RabbitListener(queues = "${demo.new-messages-queue}")
    public void handleNewMessage(RabbitSenderController.MyPayload payload)
    {
        System.out.println("You have 1 new message "+payload.content());
    }

}
