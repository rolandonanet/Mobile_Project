package com.project.presence.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.project.presence.R;
import com.project.presence.fragment.ClassStudentFragment;
import com.project.presence.fragment.HomeStudentFragment;
import com.project.presence.fragment.HomeTeacherFragment;
import com.project.presence.fragment.MissStudentFragment;
import com.project.presence.fragment.PresenceStudentFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class StudentActivity extends AppCompatActivity {
    private BottomNavigationViewEx navigationView;
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle(intent.getStringExtra("name"));
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.bottom_navigation_student);
        bottomNavigation(navigationView);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPagerStudent, new HomeStudentFragment()).commit();

    }

    public void bottomNavigation(BottomNavigationViewEx viewEx) {
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.ic_home_student:
                        fragmentTransaction.replace(R.id.viewPagerStudent, new HomeStudentFragment()).commit();
                        return true;

                    case R.id.ic_miss_student:
                        System.out.println("-------------------------- teste ----------------");
                        fragmentTransaction.replace(R.id.viewPagerStudent, new MissStudentFragment()).commit();
                        return true;

                    case R.id.ic_class_student:
                        fragmentTransaction.replace(R.id.viewPagerStudent, new ClassStudentFragment()).commit();
                        return true;

                    case R.id.ic_presence_student:
                        fragmentTransaction.replace(R.id.viewPagerStudent, new PresenceStudentFragment()).commit();
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        return super.onOptionsItemSelected(item);
    }

}
