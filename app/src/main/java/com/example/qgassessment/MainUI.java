package com.example.qgassessment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.qgassessment.Finance.FinanceFragment;
import com.example.qgassessment.Utils.ViewPagerAdapter;
import com.example.qgassessment.Weather.WeatherFragment;
import com.google.android.material.tabs.TabLayout;

public class MainUI extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ui);

        viewPager = findViewById(R.id.main_view_pager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WeatherFragment());
        adapter.addFragment(new FinanceFragment());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Weather");
        tabLayout.getTabAt(1).setText("Account");
    }
}