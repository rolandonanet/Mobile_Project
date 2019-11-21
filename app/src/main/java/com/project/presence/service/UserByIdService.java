package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.presence.model.Login;
import com.project.presence.model.User;

public class UserByIdService extends AsyncTask<String, Void, User>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected User doInBackground(String... id) {

        Gson gson = new Gson();

        String responseJson = new GenericService().request("GET", "user/"+id[0]);

        if(responseJson == null)
            return null;

        User response = gson.fromJson(responseJson, User.class);

        return response;
    }

    @Override
    protected void onPostExecute(User user){
        super.onPostExecute(user);

    }
}
