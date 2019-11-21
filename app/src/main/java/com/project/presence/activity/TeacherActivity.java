package com.project.presence.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.project.presence.R;
import com.project.presence.fragment.ClassTeacherFragment;
import com.project.presence.fragment.HomeStudentFragment;
import com.project.presence.fragment.HomeTeacherFragment;
import com.project.presence.fragment.MissTeacherFragment;
import com.project.presence.fragment.QRCodeTeacherFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;

public class TeacherActivity extends AppCompatActivity {
    private BottomNavigationViewEx navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_teacher);
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle(intent.getStringExtra("name"));
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.bottom_navigation_teacher);
        bottomNavigation(navigationView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPagerTeacher, new HomeTeacherFragment()).commit();

    }

    public void bottomNavigation(BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.ic_home_teacher :
                        fragmentTransaction.replace(R.id.viewPagerTeacher, new HomeTeacherFragment()).commit();
                        return true;

                    case R.id.ic_miss_teacher :
                        fragmentTransaction.replace(R.id.viewPagerTeacher, new MissTeacherFragment()).commit();
                        return true;

                    case R.id.ic_class_teacher :
                        fragmentTransaction.replace(R.id.viewPagerTeacher, new ClassTeacherFragment()).commit();
                        return true;

                    case R.id.ic_qrcode_teacher :
                        fragmentTransaction.replace(R.id.viewPagerTeacher, new QRCodeTeacherFragment()).commit();
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
