package com.application.app.myresturants.ui.main;

import android.content.Context;

import com.application.app.myresturants.R;
import com.application.app.myresturants.models.RestautantModel;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class RestaurantPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private String id;

    public RestaurantPagerAdapter(Context context, FragmentManager fm, String id) {
        super(fm);
        mContext = context;
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

Fragment fragment = null;
switch (position)
{
    case 0:
        fragment =   RestaurantDealFragment.newInstance(position + 1,id);
        break;
    case 1:
        fragment =  RestaurantReviewFragment.newInstance(position+1,id);
           break;

}


        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}