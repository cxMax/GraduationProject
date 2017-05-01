package com.cxmax.graduationproject.ui.main.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cxmax.graduationproject.R;
import com.cxmax.graduationproject.app.App;
import com.cxmax.graduationproject.app.Constants;
import com.cxmax.graduationproject.base.BaseActivity;
import com.cxmax.graduationproject.component.SharedPreferences.SharedPrefer;
import com.cxmax.graduationproject.presenter.MainPresenter;
import com.cxmax.graduationproject.presenter.contract.MainContract;
import com.cxmax.graduationproject.ui.main.fragment.AboutFragment;
import com.cxmax.graduationproject.ui.main.fragment.IndexFragment;
import com.cxmax.graduationproject.ui.main.fragment.SettingFragment;
import com.cxmax.graduationproject.util.SnackbarUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigationView;
    @BindView(R.id.view_search)
    MaterialSearchView searchView;

    private IndexFragment indexFragment;
    private SettingFragment settingFragment;
    private AboutFragment aboutFragment;

    MenuItem lastMenuItem;
    MenuItem searchMenuItem;
    ActionBarDrawerToggle drawerToggle;

    private int hideFragment = Constants.TYPE_INDEX;
    private int showFragment = Constants.TYPE_INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SharedPrefer.from(context)
                    .open(Constants.NIGHT_MODE_FILE)
                    .edit()
                    .putBoolean(Constants.SP_NIGHT_MODE, false)
                    .apply();
        } else {
            navigationView.getMenu().findItem(R.id.drawer_index).setChecked(false);
            toolbar.setTitle(navigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());

        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "首页");
        indexFragment = new IndexFragment();
        settingFragment = new SettingFragment();
        aboutFragment = new AboutFragment();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        lastMenuItem = navigationView.getMenu().findItem(R.id.drawer_index);
        loadMultipleRootFragment(R.id.fl_main_content, 0, indexFragment, settingFragment , aboutFragment);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_index:
                        showFragment = Constants.TYPE_INDEX;
                        searchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_setting:
                        showFragment = Constants.TYPE_SETTING;
                        searchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_about:
                        showFragment = Constants.TYPE_ABOUT;
                        searchMenuItem.setVisible(false);
                        break;
                }
                if (lastMenuItem != null) {
                    lastMenuItem.setChecked(false);
                }
                lastMenuItem = menuItem;
                menuItem.setChecked(true);
                toolbar.setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        searchView.setMenuItem(item);
        searchMenuItem = item;
        return true;
    }

    @Override
    public void showError(String msg) {
        SnackbarUtil.showShort(toolbar, msg);
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_INDEX:
                return indexFragment;
            case Constants.TYPE_SETTING:
                return settingFragment;
            case Constants.TYPE_ABOUT:
                return aboutFragment;
        }
        return indexFragment;
    }

    private int getCurrentItem(int item) {
        switch (item) {
            case Constants.TYPE_INDEX:
                return R.id.drawer_index;
            case Constants.TYPE_SETTING:
                return R.id.drawer_setting;
            case Constants.TYPE_ABOUT:
                return R.id.drawer_about;
        }
        return R.id.drawer_index;
    }

    @Override
    public void onBackPressedSupport() {
        showExitDialog();
    }

    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }
}
