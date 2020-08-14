package com.vitorvillar.mercure;

import java.util.UUID;

public class Message {

    private UUID id;
    private String data;
    private String topic;
    private Boolean private;
    private String type;

    public Message(String data, String topic) {
        this.data = data;
        this.topic = topic;
        this.id = UUID.randomUUID();
    }

    public Message(UUID id, String data, String topic, Boolean private, String type) {
        this.id = id;
        this.data = data;
        this.topic = topic;
        this.private = private;
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

    public Boolean isPrivate() {
        return private;
    }

    public void setPrivate(Boolean private) {
        this.private = private;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
