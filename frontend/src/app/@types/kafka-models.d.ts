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
  idString: String;
  host: String;
  port: number;
  rack: String;
}

export interface DescribeClusterDTO {
  nodes: KafkaNode[];
  controller: KafkaNode;
}
