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

import static com.foodquart.microservicemessaging.domain.util.TwilioMessages.*;

@Slf4j
@RequiredArgsConstructor
public class TwilioAdapter implements IMessagingProviderPort {

    private final TwilioConfig twilioConfig;

    @Override
    public boolean sendSmsNotification(NotificationModel notificationModel) {
        String logMessage;
        try {
            Message message = Message.creator(
                    new PhoneNumber(notificationModel.getPhoneNumber()),
                    new PhoneNumber(twilioConfig.getPhoneNumber()),
                    notificationModel.getMessage()
            ).create();
            logMessage = String.format(SMS_SENT_SUCCESS, notificationModel.getPhoneNumber(), message.getSid());
            log.info(logMessage);

            return true;

        } catch (ApiException e) {
            logMessage = String.format(API_ERROR, e.getMessage(), e.getCode());
            log.error(logMessage);
            return false;
        } catch (AuthenticationException e) {
            logMessage = String.format(AUTHENTICATION_ERROR, e.getMessage());
            log.error(logMessage);
            return false;
        } catch (TwilioException e) {
            logMessage = String.format(GENERAL_ERROR, e.getMessage());
            log.error(logMessage);
            return false;
        } catch (Exception e) {
            logMessage = String.format(UNEXPECTED_ERROR, e.getMessage());
            log.error(logMessage);
            return false;
        }
    }

}
