package com.ekavakakis.kafkaeye.services;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

public interface ReactiveConsumer {

    Flux<ReceiverRecord<String, String>> consume(String name);
}
