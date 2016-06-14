package com.example.zane.homework.clazzdetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;


import com.example.zane.homework.clazzdetail.presenter.HomeWorkFragment;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.clazzdetail.presenter.NoticeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailFragViewPagerAdapter extends FragmentPagerAdapter{

    private static final int COUNT = 3;
    private static final String TAG = ClazzDetailFragViewPagerAdapter.class.getSimpleName();
    private List<String> titles;
    private MemberFragment memberFragment;
    private NoticeFragment noticeFragment;
    private HomeWorkFragment homeWorkFragment;

    public ClazzDetailFragViewPagerAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>(3);
    }

    public void addHomeWorkFrag(HomeWorkFragment homeWorkFragment, String title){
        titles.add(title);
        this.homeWorkFragment = homeWorkFragment;
    }

    public void addNoticeFrag(NoticeFragment noticeFragment, String title){
        titles.add(title);
        this.noticeFragment = noticeFragment;
    }

    public void addMemberFrag(MemberFragment memberFragment, String title){
        titles.add(title);
        this.memberFragment = memberFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return homeWorkFragment;
            case 1:
                return noticeFragment;
            case 2:
                return memberFragment;
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
