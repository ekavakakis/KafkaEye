package com.ekavakakis.kafkaeye.models.dto;

import com.ekavakakis.kafkaeye.models.entities.KafkaNode;

import java.io.Serializable;
import java.util.List;

public class DescribeClusterDTO implements Serializable {

    String id;
    List<KafkaNode> nodes;
    KafkaNode controller;

    public DescribeClusterDTO() {}

    public DescribeClusterDTO(String id, List<KafkaNode> nodes, KafkaNode controller) {
        this.nodes = nodes;
        this.controller = controller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
