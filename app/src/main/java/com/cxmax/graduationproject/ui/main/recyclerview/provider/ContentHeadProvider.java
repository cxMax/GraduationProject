package com.cxmax.graduationproject.ui.main.recyclerview.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxmax.graduationproject.R;
import com.cxmax.graduationproject.component.multitype.AbsItemProvider;
import com.cxmax.graduationproject.model.bean.Course;
import com.cxmax.graduationproject.ui.main.recyclerview.viewholder.ContentHeadViewHolder;
import com.cxmax.graduationproject.ui.main.recyclerview.viewholder.ContentItemViewHolder;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class ContentHeadProvider extends AbsItemProvider<Course , ContentHeadViewHolder>{

    public ContentHeadProvider(@NonNull Context context) {
        super(context);
    }

    /**
     * determine whether this is the responsible for the given data element.
     *
     * @param item
     * @param position
     * @return
     */
    @Override
    protected boolean isForViewType(@NonNull Course item, int position) {
        return true; //cuz it only one item type in this adapter so it's always true
    }

    /**
     * this method don't need int viewtype param
     *
     * @param parent
     * @return
     */
    @NonNull
    @Override
    protected ContentHeadViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_head, parent, false);
        ContentHeadViewHolder holder = new ContentHeadViewHolder(view);
        return holder;
    }

    /**
     * @param item    data source
     * @param position The position in the datasource
     * @param holder   ViewHolder
     */
    @Override
    protected void onBindViewHolder(@NonNull Course item, int position, @NonNull ContentHeadViewHolder holder) {
        holder.update();
    }
}
