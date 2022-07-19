package com.ekavakakis.kafkaeye.models.dto;

import java.io.Serializable;

public class ConsumerGroupDTO implements Serializable {

    private String id;
    private String groupId;
    private Boolean isSimpleConsumerGroup;
    private String state;

    public ConsumerGroupDTO() {}

    public ConsumerGroupDTO(String id, String groupId, Boolean isSimpleConsumerGroup, String state) {
        this.id = id;
        this.groupId = groupId;
        this.isSimpleConsumerGroup = isSimpleConsumerGroup;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Boolean getSimpleConsumerGroup() {
        return isSimpleConsumerGroup;
    }

    public void setSimpleConsumerGroup(Boolean simpleConsumerGroup) {
        isSimpleConsumerGroup = simpleConsumerGroup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
