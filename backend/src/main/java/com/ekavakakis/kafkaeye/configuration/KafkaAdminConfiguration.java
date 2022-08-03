package com.ekavakakis.kafkaeye.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${krb.truststore.location}")
    private String trustoreLocation;

    @Value(value = "${krb.truststore.password}")
    private String truststorePassword;

    @Bean
    public AdminClient getKafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put("security.protocol", "SASL_SSL");
        configs.put("sasl.mechanism", "GSSAPI");
        configs.put("sasl.kerberos.service.name", "kafka");
        configs.put("ssl.truststore.location", trustoreLocation);
        configs.put("ssl.truststore.password", truststorePassword);
        return KafkaAdminClient.create(configs);
    }

}
