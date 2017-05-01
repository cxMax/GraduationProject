package com.cxmax.graduationproject.ui.main.recyclerview.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxmax.graduationproject.R;
import com.cxmax.graduationproject.base.BaseViewHolder;
import com.cxmax.graduationproject.model.bean.Course;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class ContentItemViewHolder extends BaseViewHolder{

    @BindView(R.id.tv_daily_item_title)
    TextView title;
    @BindView(R.id.iv_daily_item_image)
    ImageView image;

    Course course;

    public ContentItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void update() {
        title.setText(course.toString());
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
