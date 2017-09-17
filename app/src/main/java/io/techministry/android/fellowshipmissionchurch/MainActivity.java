package io.techministry.android.fellowshipmissionchurch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.techministry.android.fellowshipmissionchurch.ui.AboutUsFragment;
import io.techministry.android.fellowshipmissionchurch.ui.AnnouncementListFragment;
import io.techministry.android.fellowshipmissionchurch.ui.AudioMessagesFragment;
import io.techministry.android.fellowshipmissionchurch.ui.CalendarFragment;
import io.techministry.android.fellowshipmissionchurch.ui.LocationFragment;
import io.techministry.android.fellowshipmissionchurch.ui.PostActivity;

public class MainActivity extends AppCompatActivity {

    private BibleApi clientApi;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabsLayout;
    @BindView(R.id.main_image) ImageView mainImageView;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.btn_add_post) FloatingActionButton btnAddPost;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;


    private enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //TODO: make the database autoincrement works on each install
        //BibleDbHelper.create(this);

        clientApi = Downloader.createClientApi();

        setupViewPager(viewPager);
        tabsLayout.setupWithViewPager(viewPager);

        setupDrawerContent(navigationView);

        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            private State state;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != State.EXPANDED) {
                        ab.setHomeAsUpIndicator(R.drawable.ic_menu_primary_24dp);

                    }
                    state = State.EXPANDED;
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != State.COLLAPSED) {
                        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
                    }
                    state = State.COLLAPSED;
                } else {
                    if (state != State.IDLE) {

                    }
                    state = State.IDLE;
                }
            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    drawerLayout.closeDrawers();

                    switch (menuItem.getItemId()){
                        case android.R.id.home:

                            break;
                        case R.id.sign_out:
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(MainActivity.this,SignInActivity.class));
                            finish();
                            break;
                    }

                    return true;
                }
            });

        View view = navigationView.inflateHeaderView(R.layout.nav_header);

        final ImageView user_avatar_img = (ImageView)view.findViewById(R.id.user_avatar);
        final TextView user_name = (TextView)view.findViewById(R.id.user_name);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mDatabase.getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null){
                    String name = dataSnapshot.child("full_name").getValue().toString();
                    String user_avatar = dataSnapshot.child("photo").getValue().toString();

                    user_name.setText(name);
                    // GLide allows you to set a fall back image, placeholder image in case of a fail loading the specified image
                    Glide.with(getApplicationContext())
                            .load(user_avatar)
                            .error(R.mipmap.ic_launcher)
                            .centerCrop()
                            .fallback(R.mipmap.ic_launcher)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(user_avatar_img);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new AboutUsFragment(), "About Us");
        adapter.addFragment(new AnnouncementListFragment(), "Connect");
        adapter.addFragment(new AudioMessagesFragment(), "The Word");
//        adapter.addFragment(new CalendarFragment(), "Calendar");
        adapter.addFragment(new LocationFragment(), "Location");
        viewPager.setAdapter(adapter);

        /// This sets the view pager to the announcement fragment.
        viewPager.setCurrentItem(1);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toggleButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void toggleButton(int position) {
        if(position == 1){
            btnAddPost.setVisibility(View.VISIBLE);
        }else{
            btnAddPost.setVisibility(View.GONE);
        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }

    }

  /*  @OnClick(R.id.load_chapter)
    public void loadChapter() {
        clientApi.chapter("eng-GNTD:2Tim")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ChapterResponse>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.d("TEST", "onError");
                }

                @Override
                public void onNext(ChapterResponse chapterResponse) {
                    Log.d("TEST", "chapterResponse");
                }
            });

    }

    @OnClick(R.id.load_passages)
    public void loadPassages() {
        clientApi.passages("john+3:1-5", "eng-KJVA")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<PassagesResponse>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("Response", "error", e);
                }

                @Override
                public void onNext(PassagesResponse passagesResponse) {
                    Log.d("Response", "Test");
                }
            });
    }*/
}
