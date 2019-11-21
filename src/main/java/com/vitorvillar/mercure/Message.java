package com.vitorvillar.mercure;

import java.util.UUID;

public class Message {

    private UUID id;
    private String data;
    private String topic;
    private String target;
    private String type;

    public Message(String data, String topic) {
        this.data = data;
        this.topic = topic;
        this.id = UUID.randomUUID();
    }

    public Message(UUID id, String data, String topic, String target, String type) {
        this.id = id;
        this.data = data;
        this.topic = topic;
        this.target = target;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
