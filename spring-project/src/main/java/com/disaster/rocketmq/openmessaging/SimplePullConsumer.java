package com.disaster.rocketmq.openmessaging;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.SendResult;

public class SimplePullConsumer {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint =
                OMS.getMessagingAccessPoint("oms:rocketmq://localhost:9876/default:default");
        messagingAccessPoint.startup();
        final Producer producer = messagingAccessPoint.createProducer();
        final PullConsumer consumer = messagingAccessPoint.createPullConsumer(
                OMS.newKeyValue().put(OMSBuiltinKeys.CONSUMER_ID, "OMS_CONSUMER"));
        messagingAccessPoint.startup();
        System.out.printf("MessagingAccessPoint startup OK%n");
        final String queueName = "TopicTest";
        producer.startup();
        Message msg = producer.createBytesMessage(queueName, "Hello Open Messaging".getBytes());
        SendResult sendResult = producer.send(msg);
        System.out.printf("Send Message OK. MsgId: %s%n", sendResult.messageId());
        producer.shutdown();
        consumer.attachQueue(queueName);
        consumer.startup();
        System.out.printf("Consumer startup OK%n");
        // 运行直到发现一个消息被发送了
        boolean stop = false;
        while (!stop) {
            Message message = consumer.receive();
            if (message != null) {
                String msgId = message.sysHeaders().getString(Message.BuiltinKeys.MESSAGE_ID);
                System.out.printf("Received one message: %s%n", msgId);
                consumer.ack(msgId);
                if (!stop) {
                    stop = msgId.equalsIgnoreCase(sendResult.messageId());
                }
            } else {
                System.out.printf("Return without any message%n");
            }
        }
        consumer.shutdown();
        messagingAccessPoint.shutdown();
    }
}
