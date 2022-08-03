package com.ekavakakis.kafkaeye.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${krb.truststore.location}")
    private String trustoreLocation;

    @Value(value = "${krb.truststore.password}")
    private String truststorePassword;

    public Map<String, Object> defaultOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "kafkeye-consumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafkeye-consumer-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.kerberos.service.name", "kafka");
        props.put("ssl.truststore.location", trustoreLocation);
        props.put("ssl.truststore.password", truststorePassword);
        return props;
    }


}
