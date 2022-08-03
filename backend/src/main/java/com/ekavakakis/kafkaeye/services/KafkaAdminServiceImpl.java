package com.ekavakakis.kafkaeye.services;

import com.ekavakakis.kafkaeye.models.dto.*;
import com.ekavakakis.kafkaeye.models.entities.KafkaNode;
import com.ekavakakis.kafkaeye.models.entities.KafkaTopicPartition;
import com.ekavakakis.kafkaeye.utils.InterfaceTransformation;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.ConsumerGroupState;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.metrics.KafkaMetric;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class KafkaAdminServiceImpl implements KafkaAdminService {

    private final AdminClient adminClient;

    public KafkaAdminServiceImpl(AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    @Override
    public DescribeTopicsResult describeTopics(Collection<String> topicNames) {
        return null;
    }

    @Override
    public DescribeClusterDTO describeCluster() {
        DescribeClusterDTO dto = new DescribeClusterDTO();

        DescribeClusterResult result = adminClient.describeCluster();
        try {
            InterfaceTransformation.toKafkaNode(new ArrayList<>(result.nodes().get()));
            dto.setController(InterfaceTransformation.toKafkaNode(List.of(result.controller().get())).get(0));
            dto.setNodes(InterfaceTransformation.toKafkaNode(new ArrayList<>(result.nodes().get())));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return dto;
    }

    @Override
    public DeleteTopicsResult deleteTopics(Collection<String> topics) {
        return null;
    }

    @Override
    public Optional<List<KafkaTopicDTO>> listTopics() {
        List<KafkaTopicDTO> topicDescriptions = null;
        Collection<TopicListing> topicListings = null;
        try {
            topicListings = adminClient.listTopics()
                    .listings()
                    .get();

            DescribeTopicsResult describeTopicsResult = adminClient
                    .describeTopics(
                            topicListings
                                    .stream()
                                    .map(TopicListing::name)
                                    .toList()
                    );

            topicDescriptions = describeTopicsResult.topicNameValues()
                    .values()
                    .stream()
                    .map(this::getKafkaTopicDTO)
                    .toList();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching topic listings", e);
        }

        return Optional.ofNullable(topicDescriptions);
    }

    private KafkaTopicDTO getKafkaTopicDTO(KafkaFuture<TopicDescription> topicDescriptionKafkaFuture) {
        KafkaTopicDTO dto;
        try {
            TopicDescription topicDescription = topicDescriptionKafkaFuture.get();

            List<KafkaTopicPartition> topicPartitions = topicDescription
                    .partitions()
                    .stream()
                    .map((TopicPartitionInfo topicPartitionInfo) ->
                            new KafkaTopicPartition(
                                    topicPartitionInfo.partition(),
                                    new KafkaNode(topicPartitionInfo.leader()),
                                    InterfaceTransformation.toKafkaNode(topicPartitionInfo.replicas()),
                                    InterfaceTransformation.toKafkaNode(topicPartitionInfo.isr()))
                    ).toList();

            dto = new KafkaTopicDTO(
                    topicDescription.name(),
                    topicDescription.isInternal(),
                    topicDescription.name(),
                    topicPartitions);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching topic listings", e);
        }
        return dto;
    }

    public void deleteTopic(String topicName) {
        adminClient.deleteTopics(List.of(topicName));
    }

    @Override
    public List<ConsumerGroupDTO> listConsumerGroups(Set<String> states) {
        ListConsumerGroupsOptions options = new ListConsumerGroupsOptions();
        if (states != null && !states.isEmpty()) {
            options.inStates(states.stream().map(ConsumerGroupState::parse).collect(Collectors.toSet()));
        }
        ListConsumerGroupsResult result = adminClient.listConsumerGroups(options);
        try {
            return result.all().get()
                    .stream()
                    .map((ConsumerGroupListing listing) -> new ConsumerGroupDTO(
                            listing.groupId(),
                            listing.groupId(),
                            listing.isSimpleConsumerGroup(),
                            listing.state().map(ConsumerGroupState::toString)
                                    .orElseGet(ConsumerGroupState.UNKNOWN::toString))
                    ).toList();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Failed fetching Consumer Groups", e);
        }
    }

    @Override
    public List<ConsumerGroupDescriptionDTO> describeConsumerGroups(Set<String> groupIds) {
        return null;
    }

    @Override
    public Map<MetricName, KafkaMetric> getMetrics() {
        return (Map<MetricName, KafkaMetric>) adminClient.metrics();
    }

    @Override
    public void createTopic(CreateTopicDTO createTopicDTO) {
        adminClient.createTopics(List.of(
                new NewTopic(
                        createTopicDTO.getName(),
                        createTopicDTO.getPartitions(),
                        createTopicDTO.getReplicationFactor())
        ));
    }

}
