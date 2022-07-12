export interface KafkaTopic {
  name: string;
  uuid: string;
  internal: boolean;
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
