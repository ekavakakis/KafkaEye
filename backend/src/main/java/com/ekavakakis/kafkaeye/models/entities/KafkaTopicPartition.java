package com.ekavakakis.kafkaeye.models.entities;

import java.util.List;


public class KafkaTopicPartition {
    private int partition;
    private KafkaNode leader;
    private List<KafkaNode> replicas;
    private List<KafkaNode> isr;

    public KafkaTopicPartition() {}

    public KafkaTopicPartition(int partition, KafkaNode leader, List<KafkaNode> replicas, List<KafkaNode> isr) {
        this.partition = partition;
        this.leader = leader;
        this.replicas = replicas;
        this.isr = isr;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public KafkaNode getLeader() {
        return leader;
    }

    public void setLeader(KafkaNode leader) {
        this.leader = leader;
    }

    public List<KafkaNode> getReplicas() {
        return replicas;
    }

    public void setReplicas(List<KafkaNode> replicas) {
        this.replicas = replicas;
    }

    public List<KafkaNode> getIsr() {
        return isr;
    }

    public void setIsr(List<KafkaNode> isr) {
        this.isr = isr;
    }
}
