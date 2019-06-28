package com.junga.cupofsoju.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.junga.cupofsoju.R;

import java.util.List;

public class PageAdapter extends PagerAdapter {

    Context context;
    private final int[] drawableImgs = new int[] {
            R.drawable.intro1,
            R.drawable.intro2,
            R.drawable.intro3,
            R.drawable.intro4,

    };

    public PageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return drawableImgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Bitmap drawImg = BitmapFactory.decodeResource(context.getResources(), drawableImgs[position]);

        imageView.setImageBitmap(drawImg);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }


}


