package com.pavan.slidingmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by vipul on 27/4/15.
 */
public class Report extends FragmentActivity {

        ViewPager viewpager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_report);
            viewpager = (ViewPager) findViewById(R.id.pager);
            FragmentManager fragmentManager = getSupportFragmentManager();
            viewpager.setAdapter(new MyAdapter_report(fragmentManager));

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_time_table, menu);
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
    }

    class MyAdapter_report extends FragmentPagerAdapter {

        public MyAdapter_report(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            if(i==0)
            {
                fragment = new Subject1();
            }
            if(i==1)
            {
                fragment = new Subject2();
            }
            if(i==2)
            {
                fragment = new Subject3();
            }
            if(i==3)
            {
                fragment = new Subject4();
            }
            //if(i==4)
            //{
             //   fragment = new FridayFragment();
            //}
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = new String();
            if(position==0)
                return "Subject1";
            if(position==1)
                return "Subject2";
            if(position==2)
                return "Subject3";
            if(position==3)
                return "Subject4";
            if(position==4)
                return "Subject5";
            return null;
        }

    }

