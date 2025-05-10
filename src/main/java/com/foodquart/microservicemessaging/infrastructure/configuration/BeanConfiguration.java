package com.foodquart.microservicemessaging.infrastructure.configuration;

import com.foodquart.microservicemessaging.domain.api.INotificationServicePort;
import com.foodquart.microservicemessaging.domain.spi.IMessagingProviderPort;
import com.foodquart.microservicemessaging.domain.usecase.NotificationUseCase;
import com.foodquart.microservicemessaging.infrastructure.out.client.adapter.TwilioAdapter;
import com.twilio.Twilio;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final TwilioConfig twilioConfig;

    @Bean
    public IMessagingProviderPort messagingProviderPort() {
        return new TwilioAdapter(twilioConfig);
    }

    @Bean
    public INotificationServicePort notificationServicePort() {
        return new NotificationUseCase(messagingProviderPort());
    }

    @Bean
    public CommandLineRunner twilioInit(TwilioConfig twilioConfig) {
        return args -> Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }
}
