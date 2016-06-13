package com.example.zane.homework.homeworkdetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zane.homework.homeworkdetail.presenters.FinishedMemberFragment;
import com.example.zane.homework.homeworkdetail.presenters.NoFinishedMemberFragment;

import java.util.List;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkDetailViewpagerAdapter extends FragmentPagerAdapter{

    private List<String> titles;
    private FinishedMemberFragment finishedMemberFragment;
    private NoFinishedMemberFragment noFinishedMemberFragment;

    public HomeWorkDetailViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFinishedFragment(FinishedMemberFragment fragment, String title){
        finishedMemberFragment = fragment;
        titles.add(title);
    }

    public void addNoFinishedFragment(NoFinishedMemberFragment fragment, String title){
        noFinishedMemberFragment = fragment;
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return finishedMemberFragment;
            case 1:
                return noFinishedMemberFragment;
            default:
                return finishedMemberFragment;
        }
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
