package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;

public class StudentClassByIdService extends AsyncTask<String, Void, StudentClass>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected StudentClass doInBackground(String... id) {

        Gson gson = new Gson();

        String responseJson = new GenericService().request("GET", "user/"+id[0]);

        if(responseJson == null)
            return null;

        StudentClass response = gson.fromJson(responseJson, StudentClass.class);

        return response;
    }

    @Override
    protected void onPostExecute(StudentClass studentClass){
        super.onPostExecute(studentClass);

    }
}
