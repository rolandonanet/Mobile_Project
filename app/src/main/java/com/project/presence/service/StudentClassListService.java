package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.presence.model.StudentClass;
import com.project.presence.model.StudentClassResponse;

import java.util.List;

public class StudentClassListService extends AsyncTask<String, Void, List<StudentClass>>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected List<StudentClass> doInBackground(String... id) {

        Gson gson = new Gson();

        String responseJson = new GenericService().request("GET", "studentClass/list?pageSize=999999999");

        if(responseJson == null)
            return null;

        System.out.println(responseJson);

        StudentClassResponse response = gson.fromJson(responseJson, new TypeToken<StudentClassResponse>(){}.getType());

        List<StudentClass> parsedResponse = response.getContent();

        return parsedResponse;
    }

    @Override
    protected void onPostExecute(List<StudentClass> studentClass){
        super.onPostExecute(studentClass);

    }
}
