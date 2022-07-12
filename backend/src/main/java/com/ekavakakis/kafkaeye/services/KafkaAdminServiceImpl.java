package com.ekavakakis.kafkaeye.services;

import com.ekavakakis.kafkaeye.models.dto.DescribeClusterDTO;
import com.ekavakakis.kafkaeye.models.entities.KafkaNode;
import com.ekavakakis.kafkaeye.models.dto.KafkaTopicDTO;
import com.ekavakakis.kafkaeye.models.entities.KafkaTopicPartition;
import com.ekavakakis.kafkaeye.utils.InterfaceTransformation;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionInfo;
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

            dto.setController(InterfaceTransformation.toKafkaNode(Arrays.asList(result.controller().get())).get(0));
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
                            topicListings.stream()
                            .map(TopicListing::name)
                            .collect(Collectors.toList())
                    );

            topicDescriptions = describeTopicsResult.topicNameValues()
                    .values()
                    .stream()
                    .map((KafkaFuture<TopicDescription> topicDescriptionKafkaFuture) -> {
                        KafkaTopicDTO dto = null;
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
                                    ).collect(Collectors.toList());

                            dto = new KafkaTopicDTO(
                                    topicDescription.name(),
                                    topicDescription.isInternal(),
                                    topicDescription.topicId().toString(),
                                    topicPartitions);
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException("Error fetching topic listings", e);
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching topic listings", e);
        }

        return Optional.ofNullable(topicDescriptions);
    }

}
