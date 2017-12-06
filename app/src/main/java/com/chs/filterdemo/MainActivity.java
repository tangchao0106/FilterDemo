package com.chs.filterdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
    private TextView tv_filter;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerContent);
            }
        });
    }

    private void initView() {
        tv_filter = (TextView) findViewById(R.id.tv_filter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) findViewById(R.id.drawer_content);

        Fragment fragment = new FilterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("departmentName","");
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();



        // 初始化ViewPager并且添加适配器
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));
        // 向ViewPager绑定PagerSlidingTabStrip
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);









    }
    class myPagerAdapter extends FragmentPagerAdapter {
        String[] title = { "低压设备", "站内一次设备", "二次设备" };
        Fragment1 fragment1;
        Fragment2 fragment2;
        Fragment3 fragment3;

        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment1 = new Fragment1();
                    return fragment1;
                case 1:
                    fragment2 = new Fragment2();
                    return fragment2;
                case 2:
                    fragment3 = new Fragment3();
                    return fragment3;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {

            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

    }
}
