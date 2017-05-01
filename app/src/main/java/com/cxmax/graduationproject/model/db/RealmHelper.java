package com.cxmax.graduationproject.model.db;

import android.support.annotation.NonNull;

import com.cxmax.graduationproject.model.bean.Course;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class RealmHelper {

    private static final String DB_NAME = "curriculum .realm";

    private Realm realm;

    public RealmHelper() {
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
     * add
     * @param name
     */
    public void insertCourse(@NonNull String name) {
        insertCourse(name,0);
    }

    public void insertCourse(@NonNull String name , long date) {
        realm.beginTransaction();
        Course course = realm.createObject(Course.class , UUID.randomUUID().toString());
        course.setName(name);
        if (date != 0) {
            course.setDate(date);
        }
        course.setDate(System.currentTimeMillis());
        realm.copyToRealmOrUpdate(course);
        realm.commitTransaction();
    }

    /**
     * query single course via name
     * @param name
     * @return
     */
    public boolean queryCourseByName(@NonNull String name) {
        RealmResults<Course> results = realm.where(Course.class).findAll();
        for (Course course : results) {
            if (course.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * delete single course from db
     * @param name
     */
    public void deleteCourse(@NonNull String name){
        Course data = realm.where(Course.class).equalTo("name",name).findFirst();
        realm.beginTransaction();
        if (data != null) {
            data.deleteFromRealm();
        }
        realm.commitTransaction();
    }

    /**
     * query all course's data which are stored in db
     * @return
     */
    public List<Course> queryAll() {
        RealmResults<Course> results = realm.where(Course.class).findAllSorted("id");
        return realm.copyFromRealm(results);
    }

    /**
     * update course data
     * @param name
     * @param date
     */
    public void updateCourse(@NonNull String name , @NonNull long date){
        Course bean = realm.where(Course.class).equalTo("name", name).findFirst();
        realm.beginTransaction();
        if (bean != null) {
            bean.setDate(date);
        }
    }
}
