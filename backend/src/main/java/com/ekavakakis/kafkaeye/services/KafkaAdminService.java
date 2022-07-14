package com.ekavakakis.kafkaeye.services;

import com.ekavakakis.kafkaeye.models.dto.DescribeClusterDTO;
import com.ekavakakis.kafkaeye.models.dto.KafkaTopicDTO;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface KafkaAdminService {

    DescribeTopicsResult describeTopics(Collection<String> topicNames);

    DescribeClusterDTO describeCluster();

    DeleteTopicsResult deleteTopics(Collection<String> topics);

    Optional<List<KafkaTopicDTO>> listTopics();

    void deleteTopic(String topicName);

}
