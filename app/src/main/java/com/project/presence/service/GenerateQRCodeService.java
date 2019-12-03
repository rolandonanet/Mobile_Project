package com.project.presence.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.presence.model.Login;
import com.project.presence.model.QRCodeDTO;
import com.project.presence.model.QRCodeResponseDTO;
import com.project.presence.model.User;

public class GenerateQRCodeService extends AsyncTask<QRCodeDTO, Void, QRCodeResponseDTO>{

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected QRCodeResponseDTO doInBackground(QRCodeDTO... qrCodes) {

        Gson gson = new Gson();

        String json = gson.toJson(qrCodes[0]);

        String responseJson = new GenericService().request("POST", "user/qrcode", json);

        if(responseJson == null)
            return null;

        System.out.println(responseJson);
        QRCodeResponseDTO response = gson.fromJson(responseJson, QRCodeResponseDTO.class);

        return response;
    }

    @Override
    protected void onPostExecute(QRCodeResponseDTO qrCodeResponse){
        super.onPostExecute(qrCodeResponse);

    }
}
