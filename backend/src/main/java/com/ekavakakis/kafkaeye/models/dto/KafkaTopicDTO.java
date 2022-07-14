package com.ekavakakis.kafkaeye.models.dto;

import com.ekavakakis.kafkaeye.models.entities.KafkaTopicPartition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaTopicDTO {

    private String name;
    private boolean internal;
    private String id;
    private List<KafkaTopicPartition> topicPartitions;

}
