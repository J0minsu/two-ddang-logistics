package com.two_ddang.logistics.ai.application.service;

import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class SlackService {

    @Value("${slack.webhook.url}")
    private String slackWebHookUrl;

    private final Slack slack = Slack.getInstance();

    @Async
    public CompletableFuture<String> sendMessage(String message) throws IOException {
        WebhookResponse response = slack.send(slackWebHookUrl, message);
        return CompletableFuture.completedFuture(response.getMessage());
    }


}
