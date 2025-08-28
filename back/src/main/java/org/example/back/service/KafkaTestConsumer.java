package org.example.back.service;
import org.example.back.controller.KafkaSseController;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTestConsumer {

    private final KafkaSseController sseController;

    public KafkaTestConsumer(KafkaSseController sseController) {
        this.sseController = sseController;
    }

    @KafkaListener(topics = "random-topic", groupId = "random-group")
    public void listen(String message) {
        System.out.println("Consumed message: " + message);
        sseController.sendToClients(message); // SSE로 전송
    }
}

