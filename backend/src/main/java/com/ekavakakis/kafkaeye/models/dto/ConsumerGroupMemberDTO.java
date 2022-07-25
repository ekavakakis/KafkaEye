package com.ekavakakis.kafkaeye.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerGroupMemberDTO {

    private String clientId;
    private String consumerId;
    private String host;

}

record PartitionAssignment(){}