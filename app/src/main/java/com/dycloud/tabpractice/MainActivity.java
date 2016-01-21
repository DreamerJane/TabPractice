package com.dycloud.tabpractice;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.dycloud.tabpractice.bean.Tab;
import com.dycloud.tabpractice.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    private LayoutInflater mInflater;
    //存放Tab的列表，方便封装
    private List<Tab> mTabs = new ArrayList<Tab>(3);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab() {
        //将所需Tab初始化，并添加到列表
        Tab home = new Tab(R.string.home, R.drawable.selector_icon, HomeFragment.class);
        Tab home1 = new Tab(R.string.home, R.drawable.selector_icon, HomeFragment.class);
        Tab home2 = new Tab(R.string.home, R.drawable.selector_icon, HomeFragment.class);
        mTabs.add(home);
        mTabs.add(home1);
        mTabs.add(home2);
        //实例化FragmentTabHost
        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);
        //将Tab添加到TabHost中
        for(Tab tab :mTabs) {
            TabHost.TabSpec mTabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            mTabSpec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(mTabSpec, tab.getFragment(), null);
        }

        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);
    }
    //初始化Indicator
    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        TextView textView = (TextView) view.findViewById(R.id.text_indicator);
        imageView.setBackgroundResource(tab.getIcon());
        textView.setText(tab.getTitle());
        return view;
    }
}
