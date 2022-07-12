package com.ekavakakis.kafkaeye.utils;

import com.ekavakakis.kafkaeye.models.entities.KafkaNode;
import org.apache.kafka.common.Node;

import java.util.List;
import java.util.stream.Collectors;

public class InterfaceTransformation {

    public static List<KafkaNode> toKafkaNode(List<Node> nodes) {
        return nodes.stream().map(KafkaNode::new).collect(Collectors.toList());
    }


}
