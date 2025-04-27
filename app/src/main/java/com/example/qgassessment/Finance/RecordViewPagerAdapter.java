package com.example.qgassessment.Finance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qgassessment.Utils.ViewPagerAdapter;

import java.util.List;

public class RecordViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    String[] titles = {"支出", "收入"};
    public RecordViewPagerAdapter(@NonNull FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return titles[position];
    }
}
