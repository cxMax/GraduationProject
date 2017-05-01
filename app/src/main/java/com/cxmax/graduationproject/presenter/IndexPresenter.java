package com.cxmax.graduationproject.presenter;

import com.cxmax.graduationproject.base.RxPresenter;
import com.cxmax.graduationproject.component.RxBus.RxBus;
import com.cxmax.graduationproject.model.bean.Course;
import com.cxmax.graduationproject.model.db.RealmHelper;
import com.cxmax.graduationproject.presenter.contract.IndexContract;
import com.cxmax.graduationproject.util.Preconditions;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class IndexPresenter extends RxPresenter<IndexContract.View> implements IndexContract.Presenter {

    private RealmHelper realmHelper;

    @Inject
    public IndexPresenter(RealmHelper realmHelper) {
        this.realmHelper = realmHelper;
        registerEvent();
    }

    @Override
    public void getIndexData() {
        addSubscribe(Single.just("queryAll")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, List<Course>>() {
                    @Override
                    public List<Course> apply(@NonNull String s) throws Exception {
                        if (Preconditions.isEmpty(realmHelper.queryAll())) {
                            realmHelper.insertCourse("语文");
                            realmHelper.insertCourse("数学");
                            realmHelper.insertCourse("英语");
                            realmHelper.insertCourse("体育");
                        }
                        return realmHelper.queryAll();
                    }
                })
                .subscribe(new Consumer<List<Course>>() {
                    @Override
                    public void accept(@NonNull List<Course> courses) throws Exception {
                        if (!Preconditions.isEmpty(courses)) {
                            view.showContent(courses);
                        } else {
                            view.showError("result of queryAll is null");
                        }
                    }
                }));
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CalendarDay.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CalendarDay>() {
                    @Override
                    public void accept(@NonNull CalendarDay calendarDay) throws Exception {
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth() + 1);
                        String day = String.valueOf(calendarDay.getDay() + 1);
                        if (month.length() < 2) {
                            month = "0" + month;
                        }
                        if (day.length() < 2) {
                            day = "0" + day;
                        }
                        String selectDate = date.append(year).append(month).append(day).toString();
                        insertContent(selectDate);
                        view.addContent(selectDate);
                        getIndexData();
                    }
                })
        );
    }

    private void insertContent(String date) {
        realmHelper.insertCourse("历史" , Long.parseLong(date));
    }
}
