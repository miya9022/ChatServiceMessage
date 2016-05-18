package com.example.observer.chatservicemessage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.observer.chatservicemessage.R;
import com.example.observer.chatservicemessage.model.UserInfo;
import com.example.observer.chatservicemessage.utils.DownloadAndApplyImageTask;

import java.util.HashMap;

/**
 * Created by observer on 05/13/2016.
 */
public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private HashMap<String, UserInfo> mContacts;

    public ContactAdapter(HashMap<String, UserInfo> contacts, Context context) {
        this.mContacts = contacts;
        this.mContext = context;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        if (mContacts.size() > 0) {
            int i = 0;
            for(UserInfo user: mContacts.values()) {
                if(i == position) return (Object) user;
                i++;
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        final UserInfo userInfo = (UserInfo) getItem(position);
        if(view == null) {
            view = inflater.inflate(R.layout.item_contact, null, false);
            holder = new ViewHolder();
            holder.ava_thumb = (ImageView) view.findViewById(R.id.ava_thumb);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_email = (TextView) view.findViewById(R.id.tv_email);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        DownloadAndApplyImageTask task = new DownloadAndApplyImageTask(holder.ava_thumb);
        task.execute(userInfo.getImg_url());
        holder.tv_name.setText(userInfo.getName());
        holder.tv_email.setText(userInfo.getEmail());

        return view;
    }

    private class ViewHolder {
        ImageView ava_thumb;
        TextView tv_name, tv_email;
    }
}
