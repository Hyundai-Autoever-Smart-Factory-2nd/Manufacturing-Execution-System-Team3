package org.example.back.service;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 20) // 2초마다 메시지 발행
    public void sendRandomData() {
        int value = random.nextInt(100);
        kafkaTemplate.send("random-topic", String.valueOf(value));
        System.out.println("Published: " + value);
    }
}
