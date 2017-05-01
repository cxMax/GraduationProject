package com.cxmax.graduationproject.ui.calendar.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.cxmax.graduationproject.R;
import com.cxmax.graduationproject.base.SimpleActivity;
import com.cxmax.graduationproject.component.RxBus.RxBus;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class CalendarActivity extends SimpleActivity {

    @BindView(R.id.view_calender)
    MaterialCalendarView calender;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    CalendarDay selectDate;

    @Override
    protected int getLayout() {
        return R.layout.activity_calender;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "选择日期");
        calender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(2018, 5, 20))
//                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selectDate = date;
            }
        });
    }

    @OnClick(R.id.tv_calender_enter)
    void chooseDate() {
        RxBus.getDefault().post(selectDate);
        finish();
    }
}
