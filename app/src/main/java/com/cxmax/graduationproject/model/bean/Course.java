package com.cxmax.graduationproject.model.bean;

import com.cxmax.graduationproject.util.DateUtil;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class Course extends RealmObject{

    @PrimaryKey
    private String id;

    private String name;

    private String user = "zsm";

    private long date;

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Course{" +
                " 姓名 ='" + user + '\'' +
                ", 课程 ='" + name + '\'' +
                ", 时间 =" + DateUtil.stampToDate(String.valueOf(date)) +
                '}';
    }
}
