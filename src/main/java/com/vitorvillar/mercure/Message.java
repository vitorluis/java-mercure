package com.vitorvillar.mercure;

public class Message {

    private String data;
    private String topic;

    public Message(String data, String topic) {
        this.data = data;
        this.topic = topic;
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
}
