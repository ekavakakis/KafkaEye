export interface KafkaTopicDTO {
  name: string;
  id: string;
  internal: boolean;
  topicPartitions: KafkaTopicPartition[];
}

export interface KafkaTopicPartition {
  partition: number;
  leader: KafkaNode;
  replicas: KafkaNode[];
  isr: KafkaNode[];
}

export interface KafkaNode {
  id: number;
  idString: string;
  host: string;
  port: number;
  rack: string;
}

export interface DescribeClusterDTO {
  id: string;
  nodes: KafkaNode[];
  controller: KafkaNode;
}

export interface ConsumerGroupDTO {
  id: string;
  groupId: string;
  isSimpleConsumerGroup: boolean;
  state: string;
}

export interface CreateTopicDTO {
     name: string
     replicationFactor: number;
     partitions: number;
}
