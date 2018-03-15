package com.example.imranhishaam.temper.view;

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.imranhishaam.temper.BuildConfig;
import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.data.JobFactory;
import com.example.imranhishaam.temper.databinding.MainActivityBinding;
import com.example.imranhishaam.temper.model.JobModel;
import com.example.imranhishaam.temper.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

  private MainActivityBinding jobActivityBinding;
  private MainViewModel mainViewModel;
  private TabLayout tabLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    initDataBinding();
    setSupportActionBar(toolbar);
    setupObserver(mainViewModel);

  }

  private void initDataBinding() {
    jobActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    mainViewModel = new MainViewModel(this);
    jobActivityBinding.setMainViewModel(mainViewModel);
  }

  public void setupObserver(Observable observable) {
    observable.addObserver(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mainViewModel.reset();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_github) {
      startActivityActionView();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void startActivityActionView() {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.PROJECT_URL)));
  }

  @Override public void update(Observable observable, Object data) {
    if (observable instanceof MainViewModel) {
      MainViewModel mainViewModel = (MainViewModel) observable;
      initViewPagerAndTabs(mainViewModel.getJobList());
    }
  }

  private void initViewPagerAndTabs(Map<String, ArrayList<JobModel>> jobList ) {
    ViewPager viewPager = (ViewPager) findViewById(R.id.list_job);
    PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

    for (Map.Entry<String, ArrayList<JobModel>> list : jobList.entrySet())
    {
      pagerAdapter.addFragment(MainFragment.createInstance(list.getValue()), list.getValue(), list.getKey());
    }

    viewPager.setAdapter(pagerAdapter);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    tabLayout.setupWithViewPager(viewPager);

    int eachTabIndex = 0;

    for (Map.Entry<String, ArrayList<JobModel>> list : jobList.entrySet())
    {
      TextView eachTab = (TextView) LayoutInflater.from(this).inflate(R.layout.each_tab, null);
      eachTab.setText("Za " + list.getKey().substring(list.getKey().lastIndexOf('-') + 1) + " mei");
      tabLayout.getTabAt(eachTabIndex).setCustomView(eachTab);
      ++eachTabIndex;

    }
  }

  static class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    private final List<ArrayList<JobModel>> fragmentJobList = new ArrayList<>();

    public PagerAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    public void addFragment(Fragment fragment, ArrayList<JobModel> jobs, String title) {
      fragmentList.add(fragment);
      fragmentJobList.add(jobs);
      fragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
      return fragmentList.get(position);
    }

    @Override
    public int getCount() {
      return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
      return fragmentTitleList.get(position);
    }
  }
}
