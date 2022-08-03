package com.ekavakakis.kafkaeye.controllers.socket;

import com.ekavakakis.kafkaeye.services.ReactiveConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class TopicHandler implements WebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(TopicHandler.class);

    @Autowired
    ReactiveConsumer consumer;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        final String topicName = extractTopicName(session.getHandshakeInfo().getUri().toString());
        logger.info("Initiating data stream for topic: {}", topicName);
        return session.send(
                consumer.consume(topicName)
                        .map(ConsumerRecord::value)
                        .map(session::textMessage)
        );
    }

    private String extractTopicName(String uri) {
        String[] uriParams = uri.split("/");
        return uriParams[uriParams.length - 1];
    }

}
