package com.cxmax.graduationproject.ui.main.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;

import com.cxmax.graduationproject.R;
import com.cxmax.graduationproject.app.Constants;
import com.cxmax.graduationproject.base.BaseFragment;
import com.cxmax.graduationproject.component.RxBus.RxBus;
import com.cxmax.graduationproject.component.SharedPreferences.SharedPrefer;
import com.cxmax.graduationproject.model.event.NightModeEvent;
import com.cxmax.graduationproject.presenter.SettingPresenter;
import com.cxmax.graduationproject.presenter.contract.SettingContract;

import butterknife.BindView;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class SettingFragment extends BaseFragment<SettingPresenter> implements CompoundButton.OnCheckedChangeListener, SettingContract.View {

    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @BindView(R.id.cb_setting_voice)
    AppCompatCheckBox cbSettingVoice;

    private boolean isNull = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNull = savedInstanceState == null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        cbSettingNight.setChecked(SharedPrefer.from(context)
                .open(Constants.NIGHT_MODE_FILE)
                .read(Context.MODE_ENABLE_WRITE_AHEAD_LOGGING)
                .getBoolean(Constants.SP_NIGHT_MODE, false));
        cbSettingNight.setOnCheckedChangeListener(this);
        cbSettingVoice.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_setting_night:
                if (isNull) {   //防止夜间模式MainActivity执行reCreate后重复调用
                    SharedPrefer.from(context)
                            .open(Constants.NIGHT_MODE_FILE)
                            .edit()
                            .putBoolean(Constants.SP_NIGHT_MODE, b)
                            .apply();
                    NightModeEvent event = new NightModeEvent();
                    event.setNightMode(b);
                    RxBus.getDefault().post(event);
                }
                break;
            case R.id.cb_setting_voice:
                AudioManager audioManager = (AudioManager) context
                        .getSystemService(Context.AUDIO_SERVICE);
                if (b) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                } else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }
                break;
        }
    }
}
