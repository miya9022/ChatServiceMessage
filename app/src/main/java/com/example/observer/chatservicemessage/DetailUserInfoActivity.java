package com.example.observer.chatservicemessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.observer.chatservicemessage.app.AppController;
import com.example.observer.chatservicemessage.model.UserInfo;
import com.example.observer.chatservicemessage.utils.Global;
import com.example.observer.chatservicemessage.utils.Params;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class DetailUserInfoActivity extends AppCompatActivity {

    private EditText edt_username, edt_realname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_info);

        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_realname = (EditText) findViewById(R.id.edt_realname);
    }

    public void finish(View view) {
        Global.userLogin.setUsername(edt_username.getText().toString());
        Global.userLogin.setName(edt_realname.getText().toString());

        Firebase cursorUsers = AppController.getInstance().getRoot().child(Params.URL_USERS);
        cursorUsers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    UserInfo userInfo = child.getValue(UserInfo.class);
                    if(userInfo.getEmail().equals(Global.userLogin.getEmail())) {
                        child.getRef().setValue(Global.userLogin);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
