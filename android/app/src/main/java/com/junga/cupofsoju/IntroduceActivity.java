package com.junga.cupofsoju;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.junga.cupofsoju.Adapter.PageAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class IntroduceActivity extends AppCompatActivity {

    List<Drawable> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(ContextCompat.getDrawable(this,R.drawable.intro1));
        drawables.add(ContextCompat.getDrawable(this,R.drawable.intro2));
        drawables.add(ContextCompat.getDrawable(this,R.drawable.intro3));
        drawables.add(ContextCompat.getDrawable(this,R.drawable.intro4));

        WormDotsIndicator dotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.photos_viewpager);
        PageAdapter adapter = new PageAdapter(this);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

    }
}


