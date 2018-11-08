package com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ExploreFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ExploreFragment ServiceOrder = ExploreFragment.newInstance("orders");
                return ServiceOrder;
            case 1:
                ExploreFragment Handyman = ExploreFragment.newInstance("handyman");
                return Handyman;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
