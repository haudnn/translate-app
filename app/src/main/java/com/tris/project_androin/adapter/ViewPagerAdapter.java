package com.tris.project_androin.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tris.project_androin.activity.IntroFragment1;
import com.tris.project_androin.activity.IntroFragment3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new IntroFragment3();
            default:
                return new IntroFragment1();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
