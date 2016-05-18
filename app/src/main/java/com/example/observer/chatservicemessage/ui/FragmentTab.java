package com.example.observer.chatservicemessage.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.observer.chatservicemessage.R;
import com.example.observer.chatservicemessage.adapter.ContactAdapter;
import com.example.observer.chatservicemessage.app.AppController;
import com.example.observer.chatservicemessage.model.UserInfo;
import com.example.observer.chatservicemessage.utils.Global;
import com.example.observer.chatservicemessage.utils.Params;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

/**
 * Created by observer on 05/11/2016.
 */
public class FragmentTab extends Fragment {
    private static final String RES_ID = "resource";
    private static final String TAG = FragmentTab.class.getSimpleName();
    private View view;

    public FragmentTab() {
    }

    public static FragmentTab newInstance(int res){
        FragmentTab tab = new FragmentTab();
        Bundle arg = new Bundle();
        arg.putInt(RES_ID, res);
        tab.setArguments(arg);
        return tab;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int res_id = getArguments().getInt(RES_ID);
        view = inflater.inflate(res_id, null, false);
        setView(res_id);
        return view;
    }

    private void setView(int resId) {
        if(resId == Global.resource_tab_id[0]) {
            init_contact_tab();
        } else if(resId == Global.resource_tab_id[1]) {
            init_chat_tab();
        }
    }

    private HashMap<String, UserInfo> listContacts = new HashMap<>();
    private ContactAdapter adapter;
    private Context mContext;

    public void setContext(Context context) {
        this.mContext = context;
    }

    private void init_contact_tab() {
        final ContactTabHolder holder = new ContactTabHolder();
        holder.edt_search = (TextView) view.findViewById(R.id.edt_search);
        holder.btn_search = (ImageButton) view.findViewById(R.id.btn_search);
        holder.lv_contacts = (ListView) view.findViewById(R.id.lv_contacts);
        adapter = new ContactAdapter(listContacts, mContext);
        holder.lv_contacts.setAdapter(adapter);

        holder.btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_search = holder.edt_search.getText().toString().trim();
                applySearchAction(text_search);
            }
        });

        holder.edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("") ) {
                    String text_search = s.toString();
                    applySearchAction(text_search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void init_chat_tab() {
        final ChatTabHolder holder = new ChatTabHolder();
        holder.chat_view = (ListView) view.findViewById(R.id.chat_view);
        holder.textarea_chat = (EditText) view.findViewById(R.id.textarea_chat);
        holder.btn_send = (Button) view.findViewById(R.id.btn_send);


    }

    private void applySearchAction(final String text_search) {
        Firebase cursorUser = AppController.getInstance().getRoot().child(Params.URL_USERS);
//        Query query = cursorUser.orderByChild("email").startAt(text_search);
        cursorUser.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                listContacts.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    UserInfo userInfo = child.getValue(UserInfo.class);
                    if(!Global.userLogin.getEmail().equals(userInfo.getEmail()) && userInfo.getEmail().contains(text_search))
                        listContacts.put(child.getKey(), userInfo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG + " Error", firebaseError.getMessage());
            }
        });
    }

    private class ContactTabHolder {
        TextView edt_search;
        ImageButton btn_search;
        ListView lv_contacts;

//        @Override
//        public void applyDataChangeForListContacts(ContactAdapter contactAdapter) {
//            if(adapter != null)
//                adapter.notifyDataSetChanged();
//        }
    }

//    private interface ViewChange {
//        public void applyDataChangeForListContacts(ContactAdapter contactAdapter);
//    }

    private class ChatTabHolder {
        ListView chat_view;
        EditText textarea_chat;
        Button btn_send;
    }
}
