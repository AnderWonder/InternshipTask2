package com.zhizhkin.andrey.internshiptask2.userrequestslist;

import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments.UserRequestsFragment;
import com.zhizhkin.andrey.internshiptask2.model.UserRequest;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments.UserRequestsFragmentListView;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments.UserRequestsFragmentRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserRequestsListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserRequestsFragment.OnListViewCreatedListener {

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_requests_list_activity);
        setTitle(getString(R.string.user_requests_list_filter_status_default));
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
    }

    private void shiftWidgets(Toolbar toolbar, NavigationView navView) {
        //AppBar
        int statusBarHeight = InternshipTask2Application.getStatusBarHeight();
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
        coordLayoutParams.setMargins(0, 0, fabMargin, fabMargin + InternshipTask2Application.getNavigationBarHeight());
        mFab.setLayoutParams(coordLayoutParams);
    }

    private void initViewPager() {
        List<UserRequestsFragment> pages = new ArrayList<>();
        pages.add(UserRequestsFragmentRecyclerView.newInstance(UserRequest.StatusType.IN_PROCESS));
        pages.add(UserRequestsFragmentRecyclerView.newInstance(UserRequest.StatusType.DONE));
        pages.add(UserRequestsFragmentListView.newInstance(UserRequest.StatusType.WAITING));
        ViewPager viewPager = (ViewPager) findViewById(R.id.userRequestsListViewPager);
        if (viewPager != null) {
            viewPager.setAdapter(new UserRequestsViewPagerFragmentAdapter(getSupportFragmentManager(), pages));
            ((TabLayout) findViewById(R.id.userRequestsListTabLayout)).setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_requests_list_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((DrawerLayout) findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListViewCreated(View listView) {
        if (listView instanceof ListView) mFab.attachToListView((ListView) listView);
        if (listView instanceof RecyclerView) mFab.attachToRecyclerView((RecyclerView) listView);
    }
}
