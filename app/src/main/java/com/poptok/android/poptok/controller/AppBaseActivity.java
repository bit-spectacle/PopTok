package com.poptok.android.poptok.controller;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.cloud.WordcloudActivity_;
import com.poptok.android.poptok.controller.friend.FriendListActivity_;
import com.poptok.android.poptok.controller.post.GoogleMapFragment_;
import com.poptok.android.poptok.controller.post.PostListFragment_;
import com.poptok.android.poptok.controller.post.PostWriteActivity_;
import com.poptok.android.poptok.controller.recommend.RecommendLocationActivity_;
import com.poptok.android.poptok.controller.search.SearchFilterActivity_;
import com.poptok.android.poptok.controller.user.LoginActivity_;
import com.poptok.android.poptok.controller.user.ProfileActivity_;
import com.poptok.android.poptok.controller.user.SettingMenuActivity_;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.auth.AuthStore_;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.location.LocationCollectService;
import com.poptok.android.poptok.service.location.LocationReportService_;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.IUserFinder_;
import com.poptok.android.poptok.service.user.UserLogoutAsyncTask;
import com.poptok.android.poptok.tools.BottomNavigationViewHelper;

public class AppBaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String LOG_TAG = "AppBaseActivity";
    BroadcastReceiver receiver;

    int lastFragmentId = R.id.nav_map;
    AuthStore authStore;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setEvent(savedInstanceState, toolbar);
        setService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 기본 프레그먼트 : 맵
        setFragment(lastFragmentId);
    }

    private void setEvent(final Bundle savedInstanceState, Toolbar toolbar) {
        // 플로팅 버튼 이벤트
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordcloudActivity_.intent(AppBaseActivity.this).start();
            }
        });

        // 왼쪽 메뉴 토글 이벤트
        final DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 왼쪽 메뉴 이벤트 핸들러
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_app_base);
        final Activity activity = this;

        authStore = AuthStore_.getInstance_(activity);
        Menu menu = navigationView.getMenu();
        MenuItem userLogin =  menu.findItem(R.id.userLogin);
        MenuItem userLogOut =  menu.findItem(R.id.userLogOut);
        TextView textNick = headerView.findViewById(R.id.textNick);
        ImageView imageView = headerView.findViewById(R.id.imageView);

        MenuItem recommendList = menu.findItem(R.id.recommendList);


        if (authStore.isLogin()) {
            textNick.setText(authStore.getUserInfo().getNickname());
            //"192.168.1.20"+
//            imageView.setImageURI(Uri.parse(authStore.getUserInfo().getProfileImage()));
//            Glide.with(this).load(authStore.getUserInfo().getProfileImage()).apply(
//                    new RequestOptions().centerCrop().override(600,600)
//            ).into(imageView);

            userLogin.setVisible(false);
            userLogOut.setVisible(true);
            recommendList.setVisible(true);
        } else {
            textNick.setText("로그인해주세요");
            userLogin.setVisible(true);
            userLogOut.setVisible(false);
            recommendList.setVisible(false);
        }
        textNick.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (authStore.isLogin()) {
                    ProfileActivity_.intent(AppBaseActivity.this).start();
                }else{
                    LoginActivity_.intent(AppBaseActivity.this).start();
                }
            }
        });

        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authStore.isLogin()) {
                    ProfileActivity_.intent(AppBaseActivity.this).start();
                }else{
                    LoginActivity_.intent(AppBaseActivity.this).start();
                }
            }
        });

//        MenuItem recommendFriend = menu.findItem(R.id.recommendFriend);
        MenuItem recommendPlace = menu.findItem(R.id.recommendPlace);

//        recommendFriend.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if(authStore.isLogin())
//                {
//
//                }
//                return false;
//            }
//        });
//
//        recommendPlace.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if(authStore.isLogin())
//                {
//                    RecommendLocationActivity_.intent(activity).start();
//                }
//
//                return false;
//            }
//        });



        userLogin.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LoginActivity_.intent(activity).start();
                return false;
            }
        });
        userLogOut.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                IUserFinder iUserFinder = new IUserFinder_(activity);
                UserLogoutAsyncTask userLogoutAsyncTask = new UserLogoutAsyncTask(iUserFinder, AppBaseActivity.this);
                userLogoutAsyncTask.execute(authStore.getUserInfo().getUserNo());
                return false;
            }
        });

        headerView.findViewById(R.id.btn_menu_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nav_map Clicked" + activity + " saveInstance : " + savedInstanceState);
                setFragment(R.id.nav_map);
            }
        });

        headerView.findViewById(R.id.btn_menu_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nav_list Clicked");
                setFragment(R.id.nav_list);
            }
        });

        //
        headerView.findViewById(R.id.btn_menu_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nav_write Clicked");
                PostWriteActivity_.intent(AppBaseActivity.this).start();
            }
        });

        //chat으로 갈 때
        headerView.findViewById(R.id.btn_menu_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nav_chat Clicked");
                SearchFilterActivity_.intent(AppBaseActivity.this).startForResult(0);
            }
        });

        //X모양 눌렀을 때
        headerView.findViewById(R.id.btn_menu_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nav_close Clicked");

                DrawerLayout drawer = findViewById(R.id.drawer);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    onBackPressed();
                }
            }
        });

        //설정으로 갈때
        headerView.findViewById(R.id.btn_menu_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nav_setting Clicked");
                SettingMenuActivity_.intent(AppBaseActivity.this).start();
            }
        });

        // 하단 메뉴 이벤트 핸들러
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setService() {
        if (Config.isService) {
            Context context = getApplicationContext();
            Intent collectIntent = new Intent(context, LocationCollectService.class);
            startService(collectIntent);

            LocationReportService_.intent(context).start();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i(LOG_TAG, "onTouch Called");
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    setFragment(R.id.nav_map);
                    Log.i(LOG_TAG, "nav_map Clicked");
                    return true;
                case R.id.navigation_list:
                    setFragment(R.id.nav_list);
                    Log.i(LOG_TAG, "nav_list Clicked");
                    return true;
                case R.id.navigation_write:
                    //setFragment(R.id.nav_write);
                    PostWriteActivity_.intent(getApplicationContext()).start();
                    Log.i(LOG_TAG, "nav_write Clicked");
                    return true;
                case R.id.navigation_search:
                    Log.i(LOG_TAG, "nav_search Clicked");
                    SearchFilterActivity_.intent(AppBaseActivity.this).startForResult(0);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        setFragment(id);
        return true;
    }

    public void setFragment(int id) {
        lastFragmentId = id;
        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        switch (id) {
            case R.id.nav_map:
                fragment = new GoogleMapFragment_().builder().build();
                fragmentManager.beginTransaction().replace(R.id.content, fragment)
                        .commit();
                break;
            case R.id.nav_list:
                fragment = new PostListFragment_().builder().build();
                fragmentManager.beginTransaction().replace(R.id.content, fragment)
                        .commit();
                break;
            case R.id.nav_write:
                PostWriteActivity_.intent(this).start();
                break;
            case R.id.nav_search:
                SearchFilterActivity_.intent(this).start();
                break;
            case R.id.nav_friendList:
                FriendListActivity_.intent(this).start();
                break;
            case R.id.recommendPlace:
                RecommendLocationActivity_.intent(this).start();
                break;
//            case R.id.nav_searchFriend:
//                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
    }


    public void logoutResultHandler(JSONResult<Integer> result) {
        authStore.clear();
        Intent intent = this.getIntent();
        finish();
        startActivity(intent);
    }
}