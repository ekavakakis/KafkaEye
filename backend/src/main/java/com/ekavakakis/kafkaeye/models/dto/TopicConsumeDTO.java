package com.ekavakakis.kafkaeye.models.dto;

import java.io.Serializable;

public class TopicConsumeDTO implements Serializable {

    private String name;
    private String body;

    public TopicConsumeDTO() {
    }

    public TopicConsumeDTO(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "TopicConsumeDTO{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
