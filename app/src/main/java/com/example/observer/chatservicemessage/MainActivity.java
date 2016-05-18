package com.example.observer.chatservicemessage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.observer.chatservicemessage.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ListView lv_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        lv_user = (ListView) findViewById(R.id.lv_contacts);

        viewPager = (ViewPager) findViewById(R.id.pager_tab);
        viewPager.setAdapter(new PagerAdapter(this));
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                actionBar.setSelectedNavigationItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            @Override
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//
//            @Override
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//        };
//
//        actionBar.addTab(
//                actionBar.newTab()
//                        .setText("Contacts")
//                        .setTabListener(tabListener));
//        actionBar.addTab(
//                actionBar.newTab()
//                        .setText("Chat")
//                        .setTabListener(tabListener));
    }
}
