package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.presence.model.User;

import java.util.List;

public class UserListService extends AsyncTask<String, Void, List<User>>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected List<User> doInBackground(String... id) {

        Gson gson = new Gson();

        String responseJson = new GenericService().request("GET", "user/list");

        if(responseJson == null)
            return null;

        List<User> response = gson.fromJson(responseJson, new TypeToken<List<User>>(){}.getType());

        return response;
    }

    @Override
    protected void onPostExecute(List<User> users){
        super.onPostExecute(users);

    }
}
