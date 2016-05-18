package com.example.observer.chatservicemessage.utils;

import com.example.observer.chatservicemessage.R;
import com.example.observer.chatservicemessage.model.UserInfo;

import java.util.HashMap;

/**
 * Created by observer on 05/11/2016.
 */
public class Global {

    public static final String URL_CHAT_SERVER = "https://chat-server-db.firebaseio.com/";

    public static final String APP_PREFERENCE = "Chat_service_message";

    public static final String USER_INFO = "USER_INFO";

    public static final int [] resource_tab_id = {R.layout.contact_list, R.layout.chat_tab};

    public static HashMap<String, UserInfo> listFriendContacts = new HashMap<>();

    public static UserInfo userLogin = new UserInfo();
}
