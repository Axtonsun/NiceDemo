package com.example.axtonsun.nicedemo.XituActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * Created by AxtonSun on 2016/11/24.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    public final static String tabTitles[] = new String[]{"热门", "分享", "收藏", "关注", "关注者"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {//得到对应position的Fragment
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {//返回Fragment的数量
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {//得到对应position的Fragment的title
        return tabTitles[position];
    }
}