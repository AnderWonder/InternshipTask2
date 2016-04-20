package com.zhizhkin.andrey.internshiptask2.UserRequestsList;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.RequestsFragment;
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.RequestsFragmentListView;
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.RequestsFragmentRecyclerView;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequestsManager;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import java.util.ArrayList;

public class UserRequestsListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_manager_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViewPager();
    }

    private void initViewPager() {
        ArrayList<RequestsFragment> pages = new ArrayList<>();
        pages.add(createPage(UserRequest.StatusType.IN_PROCESS, new RequestsFragmentRecyclerView()));
        pages.add(createPage(UserRequest.StatusType.DONE, new RequestsFragmentRecyclerView()));
        pages.add(createPage(UserRequest.StatusType.WAITING, new RequestsFragmentListView()));
        ViewPager viewPager = (ViewPager) findViewById(R.id.requestsManagerViewPager);
        viewPager.setAdapter(new UserRequestsViewPagerFragmentAdapter(getSupportFragmentManager(), pages));
        ((TabLayout) findViewById(R.id.requestsManagerTabLayout)).setupWithViewPager(viewPager);
    }

    private RequestsFragment createPage(UserRequest.StatusType status, RequestsFragment fragment) {
        fragment.setRequests(UserRequestsManager.getInstance().getRequestsWithStatus(status));
        fragment.setTitle(status.toString());
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }
}
