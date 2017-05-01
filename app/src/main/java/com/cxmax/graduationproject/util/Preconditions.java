package com.cxmax.graduationproject.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Collection;

/**
 * @describe : Contains some assertions.
 * @usage :
 * Created by caixi on 17-2-24.
 */

public class Preconditions {

    private Preconditions() {

    }

    public static boolean isEmpty(Collection it) {
        return it == null || it.isEmpty();
    }

    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> T checkNotNull(T arg) {
        return checkNotNull(arg, "Argument must not be null");
    }

    public static <T> T checkNotNull(T arg, String message) {
        if (arg == null) {
            throw new NullPointerException(message);
        }
        return arg;
    }

    public static boolean assertNotNull(Object o) {
        return o != null;
    }

    public static String checkNotEmpty(String string) {
        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        return string;
    }

    public static <T extends Collection<Y>, Y> T checkNotEmpty(@NonNull T collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        return collection;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean assertHasDestroyed(@NonNull Activity activity) {
        return activity.isDestroyed() || activity.isFinishing();
    }

    public static boolean assertNotFinished(@NonNull Activity activity){
        return !activity.isFinishing() && !activity.isDestroyed();
    }

    public static boolean assertIsAdded(@NonNull Fragment fragment) {
        return fragment.isAdded();
    }

    public static void assertMainThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    public static void assertBackgroundThread() {
        if (!isOnBackgroundThread()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        }
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }
}
