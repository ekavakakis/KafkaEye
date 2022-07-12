package com.ekavakakis.kafkaeye.controllers;

import com.ekavakakis.kafkaeye.models.dto.DescribeClusterDTO;
import com.ekavakakis.kafkaeye.models.dto.KafkaTopicDTO;
import com.ekavakakis.kafkaeye.services.KafkaAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final KafkaAdminService kafkaAdminService;

    public AdminController(KafkaAdminService kafkaAdminService) {
        this.kafkaAdminService = kafkaAdminService;
    }

    @GetMapping("list-topics")
    Collection<KafkaTopicDTO> getTopics() {
        return kafkaAdminService.listTopics().get();
    }

    @GetMapping("describe-cluster")
    DescribeClusterDTO describeCluster() {
        return kafkaAdminService.describeCluster();
    }

}
