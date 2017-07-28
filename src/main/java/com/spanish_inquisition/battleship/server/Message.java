package com.spanish_inquisition.battleship.server;

public class Message {
    private int sender;
    private int recipient;
    private String content;

    public Message(int sender, int recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = message;
    }

    boolean isToRecipient(int recipient) {
        return this.recipient == recipient;
    }

    int getSender() {
        return sender;
    }

    int getRecipient() {
        return recipient;
    }

    String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", recipient=" + recipient +
                ", message='" + content + '\'' +
                '}';
    }
}