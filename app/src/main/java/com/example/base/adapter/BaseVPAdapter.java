package com.example.base.adapter;

import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class BaseVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] dataList;

    public BaseVPAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] dataList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.dataList = dataList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return dataList[position];
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }
}
