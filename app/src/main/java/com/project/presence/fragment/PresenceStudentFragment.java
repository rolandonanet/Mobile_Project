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
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan!!");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
        return inflater.inflate(R.layout.fragment_presence_student, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "Dados incorretos", Toast.LENGTH_LONG).show();
     //   IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
      //  System.out.println(result);
    }
}
