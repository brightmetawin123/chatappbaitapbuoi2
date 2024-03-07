package com.example.chatapp;

public class Message {
    private String userId;
    private String text1;

    public Message() {
    }

    public Message(String userId, String text1) {
        this.userId = userId;
        this.text1 = text1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
}