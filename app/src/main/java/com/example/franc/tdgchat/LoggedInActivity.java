package com.example.franc.tdgchat;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by franc on 01/02/2017.
 */

public class LoggedInActivity extends AppCompatActivity {
    Activity activity = this;
    Intent intent;
    Toolbar toolbar;
    FirebaseAuth mAuth;
    FirebaseUser utente;
    FragmentPagerAdapter adapterViewPager;
    TextView t1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
            case R.id.item1:
                intent = new Intent(activity,ImpostazioniActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                mAuth.getInstance().signOut();
                utente = null;
                intent = new Intent(activity,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_chatapp);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t1 = (TextView)findViewById(R.id.tv1);
        mAuth = FirebaseAuth.getInstance();
        utente = mAuth.getCurrentUser();
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return FirstFragment.newInstance(0, "CHAT");
                case 1:
                    return FirstFragment.newInstance(1, "CONTATTI");
                case 2:
                    return FirstFragment.newInstance(2, "CONTATTACI");
                default:
                    return null;
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if ( position == 0 )
                return "CHAT";
            if ( position == 1 )
                return "CONTATTI";
            if ( position == 2 )
                return "CONTATTACI";
            return null;
        }
    }
}
