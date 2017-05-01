package com.cxmax.graduationproject.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxmax.graduationproject.model.bean.DecorationItem;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    @NonNull
    private DecorationItem item;

    public CustomItemDecoration(@NonNull DecorationItem item) {
        this.item = item;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position > 0) {
            outRect.top = item.top;
            outRect.left = item.left;
            outRect.right = item.right;
            outRect.bottom = item.bottom;
        }
    }
}
