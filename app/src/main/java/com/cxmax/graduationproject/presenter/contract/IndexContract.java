package com.cxmax.graduationproject.presenter.contract;

import com.cxmax.graduationproject.base.BasePresenter;
import com.cxmax.graduationproject.base.BaseView;
import com.cxmax.graduationproject.model.bean.Course;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public interface IndexContract {
    interface View extends BaseView {
        void showContent(List<Course> courses);
        void addContent(String result);
    }
    interface Presenter extends BasePresenter<View> {
        void getIndexData();
    }
}
