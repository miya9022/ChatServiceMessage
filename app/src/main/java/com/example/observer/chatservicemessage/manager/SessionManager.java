package com.example.observer.chatservicemessage.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.observer.chatservicemessage.DetailUserInfoActivity;
import com.example.observer.chatservicemessage.MainActivity;
import com.example.observer.chatservicemessage.api.NotificationAction;
import com.example.observer.chatservicemessage.app.AppController;
import com.example.observer.chatservicemessage.model.UserInfo;
import com.example.observer.chatservicemessage.utils.Global;
import com.example.observer.chatservicemessage.utils.Params;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by observer on 05/11/2016.
 */
public class SessionManager implements NotificationAction {
    private static final String TAG = SessionManager.class.getSimpleName();
    private static Context mContext;
    private final SessionManager sessionManager = this;
    private static SharedPreferences sessionPreferences;

    public SessionManager(Context context) {
        mContext = context;
    }

    public static void login(final String email, String password) {
        AppController.getInstance().getRoot().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(mContext,
                        "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(),
                        Toast.LENGTH_LONG).show();
                Global.userLogin = new UserInfo(email, authData.getUid());
                Firebase cursorUser = AppController.getInstance().getRoot().child(Params.URL_USERS);
                cursorUser.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, dataSnapshot.toString());
                        for(DataSnapshot child : dataSnapshot.getChildren()) {
                            UserInfo userInfo = child.getValue(UserInfo.class);
                            if(Global.userLogin.getEmail().equals(userInfo.getEmail())) {
                                if(userInfo.getUsername() != null) {
                                    Global.userLogin = userInfo;
                                    AppController.getInstance().getSessionManager().setSessionPreferences(Global.userLogin);
                                    AppController.getInstance().getSessionManager().afterLoginSuccess();
                                    return;
                                } else {
                                    AppController.getInstance().getSessionManager().afterNoticeLackOfInfomation();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.d(TAG + " Error", firebaseError.getMessage());
                    }
                });


            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(mContext, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void signup(final String email, String password) {
        AppController.getInstance().getRoot().createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Toast.makeText(mContext,
                        "Successfully created user account with uid: " + result.get("uid"),
                        Toast.LENGTH_LONG).show();
                Global.userLogin = new UserInfo(email, String.valueOf(result.get("uid")));
                Intent intent = new Intent(mContext, DetailUserInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
                AppController.getInstance().getRoot().child(Params.URL_USERS).push().setValue(Global.userLogin);
//                ((NotificationAction) mContext).afterSignUpSuccess(new UserInfo(email, result.get("uid").toString()));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(mContext, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setSessionPreferences(UserInfo userInfo) {
        sessionPreferences = mContext.getSharedPreferences(Global.APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sessionPreferences.edit();
        String userJson = new Gson().toJson(userInfo);
        editor.putString(Global.USER_INFO, userJson);
        editor.commit();
    }

    @Override
    public void afterSignUpSuccess(UserInfo user) {

    }

    @Override
    public void afterLoginSuccess() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    @Override
    public void afterNoticeLackOfInfomation() {
        Intent intent = new Intent(mContext, DetailUserInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }
}
