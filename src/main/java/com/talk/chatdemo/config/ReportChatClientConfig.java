package com.talk.chatdemo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ReportChatClientConfig {

    @Bean
    ChatClient reportChatClient(ChatClient.Builder builder, ToolCallbackProvider mcpToolCallbacks) {
        return builder
                .defaultSystem("""
                        Você é um analista de dados sênior.
                        Responda SEMPRE em português, de forma objetiva e baseada nos dados retornados pelas ferramentas MCP.
                        Quando comparar períodos, mostre valores absolutos e variação percentual quando relevante.
                        Nunca invente números que não venham do banco.
                        """).defaultTools(mcpToolCallbacks)
                .build();
    }
}
