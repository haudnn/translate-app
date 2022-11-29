package com.tris.project_androin.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tris.project_androin.activity.FavoriteFragment;
import com.tris.project_androin.activity.HistoryFragment;
import com.tris.project_androin.activity.HomeFragment;

public class ViewPagerAdapterMain extends FragmentStatePagerAdapter {
    public ViewPagerAdapterMain(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println(position);
        switch (position){
            case 0:
                return new HomeFragment();
            case 2:
                return new HistoryFragment();
            default:
                return new FavoriteFragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
