package com.example.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.petagram.adapters.PageAdapter;
import com.example.petagram.fragments.ProfileFragment;
import com.example.petagram.fragments.RecyclerviewFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar actionbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionbar = (Toolbar) findViewById(R.id.action_bar_main);
        setSupportActionBar(actionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();
    }



    public void takePicture(View view) {
        Toast.makeText(this, "Picture taken", Toast.LENGTH_SHORT).show();
    }

    public void goToNextActivity(View view) {
        Intent intent = new Intent(this, FiveFavoriteAnimalsActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.contact_item:
                Intent newIntent = new Intent(this, ContactActivity.class);
                startActivity(newIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    // fragment:
    private ArrayList<Fragment> addFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerviewFragment());
        fragments.add(new ProfileFragment());

        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), 0, addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.muzzle);
    }
}