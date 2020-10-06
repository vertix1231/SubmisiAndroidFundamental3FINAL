package com.dicoding.android.fundamental.githubuserapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.fragment.FollowerFragment;
import com.dicoding.android.fundamental.githubuserapp.fragment.FollowingFragment;

public class FragmentPagerAdapterDetailProfil extends FragmentPagerAdapter {
    private final Context mContext;
    private final String login;
    public FragmentPagerAdapterDetailProfil(Context context, FragmentManager fm, String login) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        this.login = login;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = FollowerFragment.newInstance(login);
                break;
            case 1:
                fragment = FollowingFragment.newInstance(login);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
