package org.whispersystems.pushserver.senders;

import com.notnoop.apns.ApnsDelegate;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.DeliveryError;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APNSenderDelegate implements ApnsDelegate {
    private final Logger logger       = LoggerFactory.getLogger(APNSenderDelegate.class);

    public void messageSent(ApnsNotification message, boolean resent) {
        String token = Hex.encodeHexString(message.getDeviceToken());
        String payload = new String(message.getPayload());
        logger.info("APNS: Message Sent - To Device: "+token+", Payload: "+payload);
    }

    public void messageSendFailed(ApnsNotification message, Throwable e) {
        String token = Hex.encodeHexString(message.getDeviceToken());
        String payload = new String(message.getPayload());
        logger.error("APNS: Message Send Failed - To Device: "+token+", Payload: "+payload+", Error: "+String.valueOf(e));
    }

    public void connectionClosed(DeliveryError e, int messageIdentifier) {
        logger.error("APNS: Connection Closed - Delivery Error Code: "+String.valueOf(e.code()) + ", Message Identifier: "+String.valueOf(messageIdentifier));
    }

    public void cacheLengthExceeded(int newCacheLength) {
        logger.info("APNS: Cache Length Exceeded - New cache length: "+String.valueOf(newCacheLength));
    }

    public void notificationsResent(int resendCount) {
        logger.info("APNS: Notifications Resent - Resend Count: "+String.valueOf(resendCount));
    }
}