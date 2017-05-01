package com.cxmax.graduationproject.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    abstract public void update();
}
