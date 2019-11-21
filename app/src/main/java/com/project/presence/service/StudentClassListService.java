package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.presence.model.StudentClass;

import java.util.List;

public class StudentClassListService extends AsyncTask<String, Void, List<StudentClass>>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected List<StudentClass> doInBackground(String... id) {

        Gson gson = new Gson();

        String responseJson = new GenericService().request("GET", "user/list");

        if(responseJson == null)
            return null;

        List<StudentClass> response = gson.fromJson(responseJson, new TypeToken<List<StudentClass>>(){}.getType());

        return response;
    }

    @Override
    protected void onPostExecute(List<StudentClass> studentClass){
        super.onPostExecute(studentClass);

    }
}
