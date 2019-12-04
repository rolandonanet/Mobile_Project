package com.project.presence.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

//import com.google.zxing.client.android.camera.CameraConfigurationUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.camera.CameraManager;
import com.project.presence.R;
import com.project.presence.activity.LoginActivity;
import com.project.presence.activity.StudentActivity;
import com.project.presence.model.QRCodeCheckDTO;
import com.project.presence.model.QRCodeDTO;
import com.project.presence.model.QRCodeResponseDTO;
import com.project.presence.model.User;
import com.project.presence.service.CheckQRCodeService;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresenceStudentFragment extends Fragment {


    public PresenceStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_presence_student, container, false);
        final User user = (User) getArguments().getSerializable("user");

        // ALUNO
        QRCodeResponseDTO generatedQRCode = new QRCodeResponseDTO();
        QRCodeCheckDTO qrcodeCheck = new QRCodeCheckDTO();

        qrcodeCheck.setEncodedQRCode(generatedQRCode.getMessage());
        qrcodeCheck.setStudentId(user.get_id());

        QRCodeResponseDTO studentQRCodeCheck = new QRCodeResponseDTO();

        try {
            studentQRCodeCheck = new CheckQRCodeService().execute(qrcodeCheck).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan!!");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "Dados incorretos", Toast.LENGTH_LONG).show();
        //   IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        //  System.out.println(result);
    }
}
