package com.ekavakakis.kafkaeye.playground;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class ReactiveConsumer {

    private static final Logger log = LoggerFactory.getLogger(ReactiveConsumer.class.getName());

    private static final String BOOTSTRAP_SERVERS = "kafka1.daoslab.intrasoft-intl.com:9093,kafka2.daoslab.intrasoft-intl.com:9093,kafka3.daoslab.intrasoft-intl.com:9093";
    private static final String TOPIC = "ac_transaction";

    private final ReceiverOptions<String, String> receiverOptions;
    private final DateTimeFormatter dateFormat;

    public ReactiveConsumer(String bootstrapServers) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "sample-consumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.kerberos.service.name", "kafka");
        props.put("ssl.truststore.location", "C:\\Users\\ekavakak\\Dev\\KafkaEye\\backend\\krb\\client_certs.jks");
        props.put("ssl.truststore.password", "P@ssw0rd#123");
        receiverOptions = ReceiverOptions.create(props);
        dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss:SSS z dd MMM yyyy");
    }

    public static void main(String[] args) throws Exception {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        ReactiveConsumer consumer = new ReactiveConsumer(BOOTSTRAP_SERVERS);
        Disposable disposable = consumer.consumeMessages(TOPIC, latch);
        latch.await();
        disposable.dispose();
    }

    private Disposable consumeMessages(String topic, CountDownLatch latch) {
        ReceiverOptions<String, String> options = receiverOptions.subscription(Collections.singleton(topic))
                .addAssignListener(partitions -> log.debug("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> log.debug("onPartitionsRevoked {}", partitions));
        Flux<ReceiverRecord<String, String>> kafkaFlux = KafkaReceiver.create(options).receive();
        return kafkaFlux.subscribe((ReceiverRecord<String, String> stringReceiverRecord) -> {
            stringReceiverRecord.receiverOffset().acknowledge();
            latch.countDown();
        });
    }

}
