package com.cxmax.graduationproject.ui.main.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.cxmax.graduationproject.app.ViewTypeConstant;
import com.cxmax.graduationproject.component.multitype.MultiTypeAdapter;
import com.cxmax.graduationproject.component.multitype.MultiTypePool;
import com.cxmax.graduationproject.model.bean.Course;
import com.cxmax.graduationproject.ui.main.recyclerview.provider.ContentHeadProvider;
import com.cxmax.graduationproject.ui.main.recyclerview.provider.ContentItemProvider;
import com.cxmax.graduationproject.ui.main.recyclerview.viewholder.ContentItemViewHolder;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class IndexAdapter extends MultiTypeAdapter<Course>{

    public IndexAdapter(@NonNull Context context, @Nullable List<Course> data) {
        super(context, data);
    }

    @Override
    protected void registerAllProvider(@NonNull MultiTypePool pool) {
//        pool.add(ViewTypeConstant.IndexViewType.INDEX_LIST_HEADER , new ContentHeadProvider(context));
        pool.add(ViewTypeConstant.IndexViewType.INDEX_LIST_CONTENT , new ContentItemProvider(context));
    }

    @Override
    public void updateData(List<Course> newItems) {
        // notify data when changed
    }
}
