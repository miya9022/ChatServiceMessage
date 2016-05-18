package com.example.observer.chatservicemessage.model;

import android.util.SparseArray;

import java.io.Serializable;

/**
 * Created by observer on 05/13/2016.
 */
public class Conversation implements Serializable {
    private String id;
    private String uid; // creator
    private String other_ids;
    private String time_created;
    private SparseArray<MessageChat> messages;

    public Conversation() {
    }

    public Conversation(String id, String other_ids, String time_created, String uid) {
        this.id = id;
        this.other_ids = other_ids;
        this.time_created = time_created;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOther_ids() {
        return other_ids;
    }

    public void setOther_ids(String other_ids) {
        this.other_ids = other_ids;
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

    public SparseArray<MessageChat> getMessages() {
        return messages;
    }

    public void setMessages(SparseArray<MessageChat> messages) {
        this.messages = messages;
    }
}
