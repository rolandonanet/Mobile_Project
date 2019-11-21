package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.presence.model.Login;
import com.project.presence.model.Presence;
import com.project.presence.model.User;

public class PostPresenceService extends AsyncTask<Presence, Void, Presence>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Presence doInBackground(Presence... presences) {

        Gson gson = new Gson();

        String json = gson.toJson(presences[0]);

        String responseJson = new GenericService().request("POST", "user/presence/insert", json);

        if(responseJson == null)
            return null;

        Presence response = gson.fromJson(responseJson, Presence.class);

        return response;
    }

    @Override
    protected void onPostExecute(Presence presence){
        super.onPostExecute(presence);

    }
}
