package com.cxmax.graduationproject.component.SharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @describe :  SharedPreferences helper
 * @usage :
 * <p>
 * --read some key-value
 * SharedPrefer.from(context)
 *             .open("com.meizu.flyme.meepo.SOME_SHARED_PREFERENCE")  // optional
 *             .read()
 *             .getXxx(KYE, DEFAULT_VALUE);
 *
 * --edit some key-value
 * SharedPrefer.from(context)
 *             .open("com.meizu.flyme.meepo.SOME_SHARED_PREFERENCE")  // optional
 *             .edit()
 *             .putAaa(aKey, aValue)
 *             .putBbb(bKey, bValue)
 *             .apply(); // or .commit();
 * </p>
 * Created by caixi on 17-4-30.
 */

public class SharedPrefer {
    private Context mContext;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static final String DEFAULT_FILE_NAME =  "Curriculum.SharedPrefer";
    private String fileName = DEFAULT_FILE_NAME;


    private SharedPrefer(Context context){
        mContext = context;
    }

    public static SharedPrefer from(Context context) {
        return new SharedPrefer(context);
    }

    public SharedPreferences read() {
        return read(0);
    }

    public SharedPreferences read(int mode) {
        if (sp == null) {
            sp = mContext.getSharedPreferences(fileName, mode);
        }
        return sp;
    }

    public SharedPrefer open(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferences.Editor edit() {
        return edit(0);
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferences.Editor edit(int mode) {
        if (editor == null) {
            editor = read(mode).edit();
        }
        return editor;
    }
}
