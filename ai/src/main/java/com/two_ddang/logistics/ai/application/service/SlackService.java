package com.two_ddang.logistics.ai.application.service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.webhook.WebhookResponse;
import com.two_ddang.logistics.ai.domain.model.SlackEntity;
import com.two_ddang.logistics.ai.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class SlackService {

    @Value("${slack.webhook.url}")
    private String slackWebHookUrl;

    private String slackBotToken;

    private final Slack slack = Slack.getInstance();

    private final SlackRepository slackRepository;

    public void saveMessage(SlackEntity slackEntity) {
        slackRepository.save(slackEntity);
    }

    public String sendMessage(String message) throws IOException {
        WebhookResponse response = slack.send(slackWebHookUrl, message);
        return response.getMessage();
    }

    public String sendDirectMessage(String message, String slackId) throws SlackApiException, IOException {
        ChatPostMessageResponse response = slack.methods(slackBotToken).chatPostMessage(req -> req
                .channel(slackId)
                .text(message)
        );

        return response.isOk() ? response.getMessage().getText() : response.getError();

    }

}
