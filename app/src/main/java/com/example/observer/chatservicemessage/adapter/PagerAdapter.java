package com.example.observer.chatservicemessage.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.observer.chatservicemessage.ui.FragmentTab;
import com.example.observer.chatservicemessage.utils.Global;

/**
 * Created by observer on 05/11/2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public PagerAdapter(Context context) {
        super(((AppCompatActivity) context).getSupportFragmentManager());
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentTab fragmentTab = FragmentTab.newInstance(Global.resource_tab_id[position]);
        fragmentTab.setContext(mContext);
        return fragmentTab;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Contacts";
                break;
            case 1:
                title = "Chat";
                break;
        }
        return title;
    }
}
