package com.example.demo.controller;

import com.example.demo.model.entity.MessageEntity;
import com.example.demo.model.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class DemoController {
    private final MessageRepository messageRepository;

    @GetMapping
    public ResponseEntity<List<MessageEntity>> getAllMessages() {
        return ResponseEntity.ok(messageRepository.findAll());
    }
}
