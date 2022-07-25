package com.ekavakakis.kafkaeye.services;

import com.ekavakakis.kafkaeye.models.dto.*;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListConsumerGroupsOptions;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.metrics.KafkaMetric;

import java.util.*;

public interface KafkaAdminService {

    DescribeTopicsResult describeTopics(Collection<String> topicNames);

    DescribeClusterDTO describeCluster();

    DeleteTopicsResult deleteTopics(Collection<String> topics);

    Optional<List<KafkaTopicDTO>> listTopics();

    void deleteTopic(String topicName);

    List<ConsumerGroupDTO> listConsumerGroups(Set<String> states);

    List<ConsumerGroupDescriptionDTO> describeConsumerGroups(Set<String> groupIds);

    Map<MetricName, KafkaMetric> getMetrics();

    void createTopic(CreateTopicDTO createTopicDTO);

}
