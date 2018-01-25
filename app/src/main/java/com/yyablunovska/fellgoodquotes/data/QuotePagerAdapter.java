package com.yyablunovska.fellgoodquotes.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author yuliia.yablunovska on 1/24/18.
 */

public class QuotePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragments;

    public QuotePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
