package com.jnu.exp7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPage;
    private String[] tabTitles;//tab的标题
    private List<Fragment> mDatas = new ArrayList<>();//ViewPage2的Fragment容器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        //找到控件
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPage = findViewById(R.id.view_page);
        //创建适配器
        MyViewPageAdapter mAdapter = new MyViewPageAdapter(this,mDatas);
        mViewPage.setAdapter(mAdapter);

        //TabLayout与ViewPage2联动关键代码
        new TabLayoutMediator(mTabLayout, mViewPage, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        }).attach();

        //ViewPage2选中改变监听
        mViewPage.registerOnPageChangeCallback(new OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        //TabLayout的选中改变监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    //初始化数据
    private void initData() {
        tabTitles = new String[]{"图书", "新闻", "卖家"};
        FragmentOne frgOne = new FragmentOne();
        FragmentTwo frgTwo = new FragmentTwo();
        FragmentThree frgThree = new FragmentThree();
        mDatas.add(frgOne);
        mDatas.add(frgTwo);
        mDatas.add(frgThree);
    }

}
