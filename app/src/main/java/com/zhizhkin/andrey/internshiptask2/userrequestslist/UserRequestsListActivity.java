package com.zhizhkin.andrey.internshiptask2.userrequestslist;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import com.zhizhkin.andrey.internshiptask2.MyStudy2Application;
import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.data.TestDataContentProvider;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments.UserRequestsFragment;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments.UserRequestsListViewFragment;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments.UserRequestsRecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

public class UserRequestsListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserRequestsFragment.OnFragmentListViewCreatedListener, PopupMenu.OnMenuItemClickListener {

    private FloatingActionButton mFab;

    private List<Pair<String, Fragment>> mPages;

    private PopupMenu mFilterPopupMenu;

    private Menu mFilterMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_requests_list_activity);
        setTitle(getString(R.string.filter_menu_all_item));
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        NavigationView navView = ((NavigationView) findViewById(R.id.nav_view));
        if (navView != null) navView.setNavigationItemSelectedListener(this);
        initViewPager();
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        //Shift is necessary for Translucent theme in KitKat+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) shiftWidgets(toolBar, navView);
        new DataThread().execute();
    }

    private void shiftWidgets(Toolbar toolbar, NavigationView navView) {
        //AppBar
        int statusBarHeight = MyStudy2Application.getStatusBarHeight();
        AppBarLayout.LayoutParams appBarLayoutParams = ((AppBarLayout.LayoutParams) toolbar.getLayoutParams());
        appBarLayoutParams.setMargins(0, statusBarHeight, 0, 0);
        toolbar.setLayoutParams(appBarLayoutParams);
        //NavView
        FrameLayout frameLayout = (FrameLayout) navView.getHeaderView(0);
        LinearLayout.LayoutParams frameLayoutLayoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        frameLayoutLayoutParams.setMargins(0, statusBarHeight, 0, 0);
        frameLayout.setLayoutParams(frameLayoutLayoutParams);
        //FAB
        CoordinatorLayout.LayoutParams coordLayoutParams = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        int fabMargin = (int) getResources().getDimension(R.dimen.fab_margin);
        coordLayoutParams.setMargins(0, 0, fabMargin, fabMargin + MyStudy2Application.getNavigationBarHeight());
        mFab.setLayoutParams(coordLayoutParams);
    }

    private void initViewPager() {
        mPages = new ArrayList<Pair<String, Fragment>>();
        mPages.add(createPage(UserRequest.StatusType.IN_PROCESS));
        mPages.add(createPage(UserRequest.StatusType.DONE));
        mPages.add(createPage(UserRequest.StatusType.WAITING));
        ViewPager viewPager = (ViewPager) findViewById(R.id.userRequestsListViewPager);
        if (viewPager != null) {
            viewPager.setAdapter(new UserRequestsViewPagerFragmentAdapter(getSupportFragmentManager(), mPages));
            ((TabLayout) findViewById(R.id.userRequestsListTabLayout)).setupWithViewPager(viewPager);
        }
    }

    @NonNull
    private Pair<String,Fragment> createPage(UserRequest.StatusType status) {
        UserRequestsFragment userRequestsFragment;
        switch (status) {
            case IN_PROCESS:
            case DONE:
                userRequestsFragment=UserRequestsRecyclerViewFragment.newInstance(status);
                break;
            default:
                userRequestsFragment=UserRequestsListViewFragment.newInstance(status);
        }
        userRequestsFragment.setCursorLoader(new CursorLoader(this, TestDataContentProvider.PROVIDER_URI, null, String.valueOf(status.getId()), null, String.valueOf(-1)));
        return new Pair<String,Fragment>(status.toString(),userRequestsFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_requests_list_action_bar, menu);
        mFilterMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_settings:
                if (mFilterPopupMenu == null) initFilterPopupMenu();
                mFilterPopupMenu.show();
                break;
            case android.R.id.home:
                ((DrawerLayout) findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void initFilterPopupMenu() {
        mFilterPopupMenu = new PopupMenu(this, findViewById(R.id.filter_settings));
        mFilterPopupMenu.getMenu().add(0, -1, 0, R.string.filter_menu_all_item);
        for (UserRequest.RequestType requestType : UserRequest.RequestType.values())
            mFilterPopupMenu.getMenu().add(0, requestType.getId(), 0, requestType.toString());
        mFilterPopupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == -1){
            mFilterMenu.getItem(0).setIcon(R.drawable.ic_filter_not_set);
            setTitle(getString(R.string.filter_menu_all_item));
        }
        else {
            mFilterMenu.getItem(0).setIcon(R.drawable.ic_filter_set);
            setTitle(UserRequest.RequestType.getById(item.getItemId()).toString());
        }
        for (Pair<String, Fragment> page : mPages) {
            UserRequestsFragment fragment = ((UserRequestsFragment) page.second);
            fragment.setCursorLoader(new CursorLoader(this, TestDataContentProvider.PROVIDER_URI,
                    null, String.valueOf(fragment.getUserRequestsStatusType().getId()), null, String.valueOf(item.getItemId())));
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentListViewCreated(View listView) {
        if (listView instanceof ListView)
            mFab.attachToListView((ListView) listView);
        if (listView instanceof RecyclerView)
            mFab.attachToRecyclerView((RecyclerView) listView);
    }

    private class DataThread extends AsyncTask<Void, Void, Void> {

        private final int INTERVAL = 3;

        @Override
        protected Void doInBackground(Void... params) {
            ContentValues contentValues = new ContentValues();
            long lastTime = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - lastTime > INTERVAL * 1000) {
                    for (UserRequest.StatusType status : UserRequest.StatusType.values()) {
                        contentValues.put("status", status.getId());
                        getContentResolver().insert(TestDataContentProvider.PROVIDER_URI, contentValues);
                    }
                    lastTime = System.currentTimeMillis();
                }
            }
        }
    }

}
