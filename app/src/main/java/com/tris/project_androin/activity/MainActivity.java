package com.tris.project_androin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tris.project_androin.R;
import com.tris.project_androin.adapter.ViewPagerAdapterMain;

public class MainActivity extends AppCompatActivity  {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private Toast mToast;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_pager);

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_favorite:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_history:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        ViewPagerAdapterMain viewPagerAdapterMain = new ViewPagerAdapterMain(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapterMain);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_history).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
            super.onBackPressed();
            return;
        }else {
            mToast = Toast.makeText(MainActivity.this,"Ấn Back một lần nữa để thoát", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    private void setUpViewPager() {

    }


}