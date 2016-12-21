package io.techministry.android.fellowshipmissionchurch;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.techministry.android.fellowshipmissionchurch.ui.ElementListFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BibleApi clientApi;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabsLayout;

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
        //BibleDbHelpher.create(this);

        clientApi = Downloader.createClientApi();

        setupViewPager(viewPager);
        tabsLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ElementListFragment(), "Item 1");
        adapter.addFragment(new ElementListFragment(), "Item 2");
        adapter.addFragment(new ElementListFragment(), "Item 3");
        adapter.addFragment(new ElementListFragment(), "Item 4");
        adapter.addFragment(new ElementListFragment(), "Item 5");
        viewPager.setAdapter(adapter);
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

   /* private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
    }*/

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
