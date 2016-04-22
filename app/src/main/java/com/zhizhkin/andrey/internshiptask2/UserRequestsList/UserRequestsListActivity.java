package com.zhizhkin.andrey.internshiptask2.UserRequestsList;

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
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.UserRequestsFragment;
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.UserRequestsFragmentListView;
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.UserRequestsFragmentRecyclerView;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequestsManager;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import java.util.ArrayList;

public class UserRequestsListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.user_requests_list_filter_status_default));
        setContentView(R.layout.user_requests_list_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        initViewPager();
        //Shift is necessary for Translucent theme in KitKat+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //AppBar
            int statusBarHeight=InternshipTask2Application.getStatusBarHeight();
            AppBarLayout.LayoutParams clp = ((AppBarLayout.LayoutParams)toolbar.getLayoutParams());
            clp.setMargins(0,statusBarHeight,0,0);
            toolbar.setLayoutParams(clp);
            //NavView
            FrameLayout frl = (FrameLayout) navView.getHeaderView(0);
            LinearLayout.LayoutParams frlp = (LinearLayout.LayoutParams) frl.getLayoutParams();
            frlp.setMargins(0,statusBarHeight, 0, 0);
            frl.setLayoutParams(frlp);
            //FAB
            CoordinatorLayout.LayoutParams llp = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
            int fabMargin = (int)getResources().getDimension(R.dimen.fab_margin);
            llp.setMargins(0, 0, fabMargin, fabMargin + InternshipTask2Application.getNavigationBarHeight());
            mFab.setLayoutParams(llp);
        }
    }

    private void initViewPager() {
        final ArrayList<UserRequestsFragment> pages = new ArrayList<>();
        pages.add(createPage(UserRequest.StatusType.IN_PROCESS, new UserRequestsFragmentRecyclerView()));
        pages.add(createPage(UserRequest.StatusType.DONE, new UserRequestsFragmentRecyclerView()));
        pages.add(createPage(UserRequest.StatusType.WAITING, new UserRequestsFragmentListView()));

        ViewPager viewPager = (ViewPager) findViewById(R.id.requestsManagerViewPager);
        viewPager.setAdapter(new UserRequestsViewPagerFragmentAdapter(getSupportFragmentManager(), pages));
        ((TabLayout)findViewById(R.id.requestsManagerTabLayout)).setupWithViewPager(viewPager);
    }

    private UserRequestsFragment createPage(UserRequest.StatusType status, UserRequestsFragment fragment) {
        fragment.setRequests(UserRequestsManager.getInstance().getRequestsWithStatus(status));
        fragment.setTitle(status.toString());
        fragment.setFab(mFab);
        return fragment;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
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
                ((DrawerLayout)findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

}