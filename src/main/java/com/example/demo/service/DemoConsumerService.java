package com.example.demo.service;

import com.example.demo.model.MessageEvent;
import com.example.demo.model.MessageWrapper;
import com.example.demo.model.entity.MessageEntity;
import com.example.demo.model.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DemoConsumerService {
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        log.info("Received raw message: {}", message);

        try {
            MessageWrapper wrapper = objectMapper.readValue(message, MessageWrapper.class);
            // Save to database
            MessageEntity entity = new MessageEntity(
                    wrapper.getUuid(),
                    wrapper.getReferralNumber(),
                    wrapper.getAmount(),
                    wrapper.getCurrency(),
                    wrapper.getTerminalId()
            );

            messageRepository.save(entity);
            log.info("Message saved to database with ID: {}", entity.getId());

        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
        }
    }

}
