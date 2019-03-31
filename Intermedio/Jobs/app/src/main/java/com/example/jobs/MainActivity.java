package com.example.jobs;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnMenu;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();


        bnMenu = findViewById(R.id.bnMenu);

        bnMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_search:
                        //Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();

                        fragment = new SearchFragment();
                        break;

                    case R.id.action_favorites:
                        //Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                        fragment = new FavoritesFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();

                return true;
            }
        });
    }
}
