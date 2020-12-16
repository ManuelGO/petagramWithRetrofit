package com.example.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SetAccount extends AppCompatActivity {

    Toolbar childActionbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_account);

        childActionbar = (Toolbar) findViewById(R.id.action_bar3);
        setSupportActionBar(childActionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
}