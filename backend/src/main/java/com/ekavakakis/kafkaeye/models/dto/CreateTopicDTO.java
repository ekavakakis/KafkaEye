package com.ekavakakis.kafkaeye.models.dto;

import java.io.Serializable;

public class CreateTopicDTO implements Serializable {

    private String name;
    private Short replicationFactor;
    private Integer partitions;

    public CreateTopicDTO() {
    }

    public CreateTopicDTO(String name, Short replicationFactor, Integer partitions) {
        this.name = name;
        this.replicationFactor = replicationFactor;
        this.partitions = partitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    public Integer getPartitions() {
        return partitions;
    }

    public void setPartitions(Integer partitions) {
        this.partitions = partitions;
    }

    @Override
    public String toString() {
        return "CreateTopicDTO{" +
                "name='" + name + '\'' +
                ", replicationFactor=" + replicationFactor +
                ", partitions=" + partitions +
                '}';
    }
}
