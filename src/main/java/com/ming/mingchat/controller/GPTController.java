package com.ming.mingchat.controller;

import com.ming.mingchat.service.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    private final OpenAIService openAIService;

    public GPTController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public String askGpt(@RequestBody String userMessage) {
        return openAIService.getChatCompletions(userMessage);
    }
}
