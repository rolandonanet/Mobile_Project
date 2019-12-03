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
import com.project.presence.fragment.MissTeacherFragment;
import com.project.presence.fragment.PresenceStudentFragment;
import com.project.presence.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class StudentActivity extends AppCompatActivity {
    private BottomNavigationViewEx navigationView;
    Intent intent;
    User user;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        System.out.println(user);
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle(user.getName());
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.bottom_navigation_student);
        bottomNavigation(navigationView);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeStudentFragment studentFragment = new HomeStudentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        studentFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.viewPagerStudent, studentFragment).commit();
    }

    public void bottomNavigation(BottomNavigationViewEx viewEx) {
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.ic_home_student:
                        HomeStudentFragment homeStudentFragment= new HomeStudentFragment();
                        bundle.putSerializable("user", user);
                        homeStudentFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerStudent, homeStudentFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerStudent, new HomeStudentFragment()).commit();
                        return true;

                    case R.id.ic_miss_student:
                        MissStudentFragment missStudentFragment= new MissStudentFragment();
                        bundle.putSerializable("user", user);
                        missStudentFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerStudent, missStudentFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerStudent, new MissStudentFragment()).commit();
                        return true;

                    case R.id.ic_class_student:
                        ClassStudentFragment classStudentFragment= new ClassStudentFragment();
                        bundle.putSerializable("user", user);
                        classStudentFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerStudent, classStudentFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerStudent, new ClassStudentFragment()).commit();
                        return true;

                    case R.id.ic_presence_student:
                        PresenceStudentFragment presenceStudentFragment= new PresenceStudentFragment();
                        bundle.putSerializable("user", user);
                        presenceStudentFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerStudent, presenceStudentFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerStudent, new PresenceStudentFragment()).commit();
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
