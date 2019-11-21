package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.presence.model.Presence;

public class UpdatePresenceService extends AsyncTask<Presence, Void, Presence>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Presence doInBackground(Presence... presences) {

        Gson gson = new Gson();

        String json = gson.toJson(presences[0]);

        String responseJson = new GenericService().request("PUT", "user/presence/update", json);

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
