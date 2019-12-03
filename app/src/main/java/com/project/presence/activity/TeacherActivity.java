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
import com.project.presence.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

public class TeacherActivity extends AppCompatActivity {
    private BottomNavigationViewEx navigationView;
    Intent intent;
    User user;
    TextView registrationTV;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_teacher);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        System.out.println(user);
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle(user.getName());

        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.bottom_navigation_teacher);
        bottomNavigation(navigationView);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeTeacherFragment teacherFragment = new HomeTeacherFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        teacherFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.viewPagerTeacher, teacherFragment).commit();

    }

    public void bottomNavigation(BottomNavigationViewEx viewEx) {
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.ic_home_teacher:
                        HomeTeacherFragment homeTeacherFragment = new HomeTeacherFragment();
                        bundle.putSerializable("user", user);
                        homeTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, homeTeacherFragment).commit();
                        //fragmentTransaction.replace(R.id.viewPagerTeacher, new HomeTeacherFragment()).commit();
                        return true;

                    case R.id.ic_miss_teacher:
                        MissTeacherFragment missTeacherFragment = new MissTeacherFragment();
                        bundle.putSerializable("user", user);
                        missTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, missTeacherFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerTeacher, new MissTeacherFragment()).commit();
                        return true;

                    case R.id.ic_class_teacher:
                        ClassTeacherFragment classTeacherFragment = new ClassTeacherFragment();
                        bundle.putSerializable("user", user);
                        classTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, classTeacherFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerTeacher, new ClassTeacherFragment()).commit();
                        return true;

                    case R.id.ic_qrcode_teacher:
                        QRCodeTeacherFragment qrCodeTeacherFragment = new QRCodeTeacherFragment();
                        bundle.putSerializable("user", user);
                        qrCodeTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, qrCodeTeacherFragment).commit();
//                        fragmentTransaction.replace(R.id.viewPagerTeacher, new QRCodeTeacherFragment()).commit();
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
