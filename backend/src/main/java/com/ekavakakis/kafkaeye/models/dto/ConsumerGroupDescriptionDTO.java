package com.ekavakakis.kafkaeye.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerGroupDescriptionDTO {

    private String id;
    private String grouoId;
    private List<ConsumerGroupMemberDTO> members;
}
