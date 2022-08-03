package com.ekavakakis.kafkaeye.utils;

import com.ekavakakis.kafkaeye.configuration.KafkaConsumerConfiguration;
import com.ekavakakis.kafkaeye.services.ReactiveConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
public class DefaultReactiveConsumer implements ReactiveConsumer {

    private final Logger logger = LoggerFactory.getLogger(DefaultReactiveConsumer.class);

    @Autowired
    KafkaConsumerConfiguration consumerConfiguration;

    @Override
    public Flux<ReceiverRecord<String, String>> consume(String topic) {
        Map<String, Object> props = consumerConfiguration.defaultOptions();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        ReceiverOptions<String, String> reactiveConsumerOptions = ReceiverOptions.create(props);
        ReceiverOptions<String, String> options = reactiveConsumerOptions.subscription(Collections.singleton(topic))
                .addAssignListener(partitions -> logger.debug("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> logger.debug("onPartitionsRevoked {}", partitions));
        return KafkaReceiver.create(options).receive();
    }
}
