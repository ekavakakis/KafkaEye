package com.ekavakakis.kafkaeye.models.entities;

import org.apache.kafka.common.Node;

public class KafkaNode {
    private int id;
    private String idString;
    private String host;
    private int port;
    private String rack;

    public KafkaNode() {}

    public KafkaNode(Node node) {
        this.id = node.id();
        this.idString = node.idString();
        this.host = node.host();
        this.port = node.port();
        this.rack = node.rack();
    }

    public KafkaNode(int id, String idString, String host, int port, String rack) {
        this.id = id;
        this.idString = idString;
        this.host = host;
        this.port = port;
        this.rack = rack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }
}
