package com.foodquart.microservicemessaging.infrastructure.out.client.adapter;

import com.foodquart.microservicemessaging.domain.model.NotificationModel;
import com.foodquart.microservicemessaging.domain.spi.IMessagingProviderPort;
import com.foodquart.microservicemessaging.infrastructure.configuration.TwilioConfig;
import com.twilio.exception.ApiException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TwilioAdapter implements IMessagingProviderPort {

    private final TwilioConfig twilioConfig;

    @Override
    public boolean sendSmsNotification(NotificationModel notificationModel) {
        try {
            String formattedMessage = String.format(
                    "Your order #%d is ready. Security PIN: %s",
                    notificationModel.getOrderId(),
                    notificationModel.getSecurityPin()
            );

            Message message = Message.creator(
                    new PhoneNumber(notificationModel.getPhoneNumber()),
                    new PhoneNumber(twilioConfig.getPhoneNumber()),
                    formattedMessage
            ).create();

            log.info("SMS successfully sent to {} - SID: {}",
                    notificationModel.getPhoneNumber(),
                    message.getSid());

            return true;

        } catch (ApiException e) {
            log.error("Twilio API error when sending SMS: {} - Code: {}",
                    e.getMessage(), e.getCode());
            return false;
        } catch (AuthenticationException e) {
            log.error("Authentication error with Twilio: {}", e.getMessage());
            return false;
        } catch (TwilioException e) {
            log.error("Twilio General Error: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Unexpected error while sending SMS: {}", e.getMessage());
            return false;
        }
    }

}
