package com.nalbam.bot.listener;

import com.nalbam.bot.repository.SlackRepository;
import com.nalbam.common.util.PackageUtil;
import in.ashwanthkumar.slack.webhook.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${app.product}")
    private String product;

    @Value("${app.profile}")
    private String profile;

    @Autowired
    private SlackRepository slackRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Context refreshed : [{}] [{}] [{}]", this.product, this.profile, event.getTimestamp());

        final Map<String, String> data = PackageUtil.getData(this.getClass());

        final SlackMessage message = new SlackMessage("Context refreshed ")
                .code(this.product).text(" ").code(this.profile).text(" ").code(data.get("version"));
        this.slackRepository.send(message);
    }

}
