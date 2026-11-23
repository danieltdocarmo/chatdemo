package com.talk.chatdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ChatClient chatClient;

    public record Request(String question) {
    }

    @PostMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> report(@RequestBody Request request) {
        long init = System.currentTimeMillis();
        log.info("Question received: {}", request.question());

        String question = chatClient.prompt()
                .user(request.question())
                .call()
                .content();

        long ms = System.currentTimeMillis() - init;
        log.info("analysis finished in {} ms", ms);

        return Map.of(
                "durationms", String.valueOf(ms),
                "analysis", question
        );
    }
}
