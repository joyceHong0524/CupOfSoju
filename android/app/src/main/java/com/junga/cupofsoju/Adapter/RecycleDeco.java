package com.junga.cupofsoju.Adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleDeco extends RecyclerView.ItemDecoration {
    private int divHeight = 5;

    public RecycleDeco(int divHeight) {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = divHeight;
    }
}

