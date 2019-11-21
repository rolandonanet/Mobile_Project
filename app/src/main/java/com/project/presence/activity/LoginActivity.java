package com.project.presence.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.presence.R;
import com.project.presence.model.Login;
import com.project.presence.model.Presence;
import com.project.presence.model.Schedule;
import com.project.presence.model.User;
import com.project.presence.service.LoginService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogin, editPassword;
    private Button buttonAccess;
    private ProgressBar progressBar;
    private String email,password;
    private Login login;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
        buttonAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editLogin.getText().toString();
                password = editPassword.getText().toString();
                login.setEmail(email);
                login.setPassword(password);
                progressBar.setVisibility(view.VISIBLE);
                try {
                    user = new LoginService().execute(login).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(user);
                userValidated(user, view);
            }
        });
    }

    private void userValidated(User user, View view){
        Intent intent;
        if(user == null){
            progressBar.setVisibility(view.GONE);
            Toast.makeText(LoginActivity.this, "Dados incorretos", Toast.LENGTH_LONG).show();
        }else if (user.getUserType().equals("teacher")){
                intent = new Intent(getApplicationContext(), TeacherActivity.class);
                intent.putExtra("id", user.get_id());
                intent.putExtra("name", user.getName());
                startActivity(intent);
                finish();
        }else if (user.getUserType().equals("student")){
            intent = new Intent(getApplicationContext(), StudentActivity.class);
            intent.putExtra("id", user.get_id());
            intent.putExtra("name", user.getName());
            startActivity(intent);
                finish();
        }else{
            progressBar.setVisibility(view.GONE);
            Toast.makeText(LoginActivity.this, "Dados incorretos", Toast.LENGTH_LONG).show();
        }

    }

    private void initializeComponents(){
        login = new Login();
        editLogin =  findViewById(R.id.editLogin);
        editPassword =  findViewById(R.id.editPassword);
        buttonAccess = findViewById(R.id.buttonAccess);
        progressBar =  findViewById(R.id.progressBar);
    }
}
