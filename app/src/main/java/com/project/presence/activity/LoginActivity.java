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
import com.project.presence.service.UserService;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogin, editPassword;
    private Button buttonAccess;
    private ProgressBar progressBar;
    private String email,password;
    private Login login;
    private UserService service;
    private String userValid;

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
                userValid = service.login(login);
                userValidated(userValid, view);
            }
        });
    }

    private void userValidated(String userValid, View view){

        if (userValid.equals("Professor")){
                startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                finish();
        }else if (userValid.equals("Aluno")){
                startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                finish();
        }else{
            progressBar.setVisibility(view.GONE);
            Toast.makeText(LoginActivity.this, "Dados incorretos", Toast.LENGTH_LONG).show();
        }

    }

    private void initializeComponents(){
        login = new Login();
        service = new UserService();
        editLogin =  findViewById(R.id.editLogin);
        editPassword =  findViewById(R.id.editPassword);
        buttonAccess = findViewById(R.id.buttonAccess);
        progressBar =  findViewById(R.id.progressBar);
    }
}
