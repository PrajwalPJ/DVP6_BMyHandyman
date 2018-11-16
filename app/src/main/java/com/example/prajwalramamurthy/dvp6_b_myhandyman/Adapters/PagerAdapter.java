// Prajwal Ramamurthy
// B-MyHandyman
// DVP 6

package com.example.prajwalramamurthy.dvp6_b_myhandyman.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.prajwalramamurthy.dvp6_b_myhandyman.Fragments.ExploreFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        // create new instance based on tabs selected
        switch (position) {
            case 0:
                return ExploreFragment.newInstance("orders");
            case 1:
                return ExploreFragment.newInstance("handyman");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
