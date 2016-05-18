package com.example.observer.chatservicemessage.app;

import android.app.Application;

import com.example.observer.chatservicemessage.manager.SessionManager;
import com.example.observer.chatservicemessage.utils.Global;
import com.firebase.client.Firebase;

/**
 * Created by observer on 05/11/2016.
 */
public class AppController extends Application {

    private Firebase root;
    private SessionManager sessionManager;
    private static AppController instance;

    public synchronized static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Firebase.setAndroidContext(this);
        root = new Firebase(Global.URL_CHAT_SERVER);
        sessionManager = new SessionManager(this);
    }

    public Firebase getRoot() {
        return root;
    }

    public void setRoot(Firebase root) {
        this.root = root;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

//    @Override
//    public void afterSignUpSuccess(UserInfo user) {
//        root.child(Params.URL_USERS).push().setValue(user);
//    }
}
