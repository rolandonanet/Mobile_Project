package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.presence.model.Login;
import com.project.presence.model.User;

public class UserService extends AsyncTask<Login, Void, User>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected User doInBackground(Login... logins) {

        Gson gson = new Gson();

        String json = gson.toJson(logins[0]);

        String responseJson = new GenericService().request("POST", "user/login", json);

        User response = gson.fromJson(responseJson, User.class);

        return response;
    }

    @Override
    protected void onPostExecute(User user){
        super.onPostExecute(user);

    }
}
