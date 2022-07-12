package com.ekavakakis.kafkaeye.models.dto;

import com.ekavakakis.kafkaeye.models.entities.KafkaNode;

import java.util.List;

public class DescribeClusterDTO {

    List<KafkaNode> nodes;
    KafkaNode controller;

    public DescribeClusterDTO() {}

    public DescribeClusterDTO(List<KafkaNode> nodes, KafkaNode controller) {
        this.nodes = nodes;
        this.controller = controller;
    }

    public List<KafkaNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<KafkaNode> nodes) {
        this.nodes = nodes;
    }

    public KafkaNode getController() {
        return controller;
    }

    public void setController(KafkaNode controller) {
        this.controller = controller;
    }
}
