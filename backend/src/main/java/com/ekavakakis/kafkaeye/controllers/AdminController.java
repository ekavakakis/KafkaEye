package com.ekavakakis.kafkaeye.controllers;

import com.ekavakakis.kafkaeye.models.dto.ConsumerGroupDTO;
import com.ekavakakis.kafkaeye.models.dto.CreateTopicDTO;
import com.ekavakakis.kafkaeye.models.dto.DescribeClusterDTO;
import com.ekavakakis.kafkaeye.models.dto.KafkaTopicDTO;
import com.ekavakakis.kafkaeye.services.KafkaAdminService;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.metrics.KafkaMetric;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final KafkaAdminService kafkaAdminService;

    public AdminController(KafkaAdminService kafkaAdminService) {
        this.kafkaAdminService = kafkaAdminService;
    }

    @GetMapping("list-topics")
    Collection<KafkaTopicDTO> getTopics() {
        return kafkaAdminService.listTopics().get();}

    @GetMapping("describe-cluster")
    DescribeClusterDTO describeCluster() {
        return kafkaAdminService.describeCluster();
    }

    @DeleteMapping("topic/{name}")
    void deleteTopic(@PathVariable("name") String name) {
        kafkaAdminService.deleteTopic(name);
    }

    @GetMapping("consumer-groups")
    public List<ConsumerGroupDTO> getConsumerGroups(@RequestParam(required = false) Set<String> states) {
        return kafkaAdminService.listConsumerGroups(states);
    }

    @GetMapping("metrics")
    public Map<MetricName, KafkaMetric> getMetrics() {
        return kafkaAdminService.getMetrics();
    }

    @PostMapping("create-topic")
    public void createTopic(@RequestBody CreateTopicDTO createTopicDTO) {
        kafkaAdminService.createTopic(createTopicDTO);
    }

}
