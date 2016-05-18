package com.example.observer.chatservicemessage.model;

import java.io.Serializable;

/**
 * Created by observer on 05/13/2016.
 */
public class MessageChat implements Serializable {
    private String id;
    private String message;
    private String uid;             // sender
    private String time_created;
    private String conversation_id;

    public MessageChat() {
    }

    public MessageChat(String message, String time_created, String uid) {
        this.message = message;
        this.time_created = time_created;
        this.uid = uid;
    }

    public MessageChat(String id, String message, String time_created, String uid) {
        this.id = id;
        this.message = message;
        this.time_created = time_created;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime_created() {
        return time_created;
    }

    public void setTime_created(String time_created) {
        this.time_created = time_created;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }
}
