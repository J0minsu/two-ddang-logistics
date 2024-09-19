package com.two_ddang.logistics.ai.application.service;

import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import com.two_ddang.logistics.ai.domain.model.SlackEntity;
import com.two_ddang.logistics.ai.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SlackService {

    @Value("${slack.webhook.url}")
    private String slackWebHookUrl;

    private final Slack slack = Slack.getInstance();

    private final SlackRepository slackRepository;

    public void saveMessage(SlackEntity slackEntity) {
        slackRepository.save(slackEntity);
    }

    public String sendMessage(String message) throws IOException {
        WebhookResponse response = slack.send(slackWebHookUrl, message);
        return response.getMessage();
    }

}
