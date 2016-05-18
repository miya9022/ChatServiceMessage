package com.example.observer.chatservicemessage.api;

import com.example.observer.chatservicemessage.model.UserInfo;

/**
 * Created by observer on 05/11/2016.
 */
public interface NotificationAction {
    void afterSignUpSuccess(UserInfo user);

    void afterLoginSuccess();

    void afterNoticeLackOfInfomation();
}
