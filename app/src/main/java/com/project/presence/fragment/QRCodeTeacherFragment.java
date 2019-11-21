package com.project.presence.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.project.presence.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QRCodeTeacherFragment extends Fragment {


    public QRCodeTeacherFragment() {
        // Required empty public constructor
    }

    ImageView qrcodeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String texto = "abcdsfasdfasdfasdfadsfasdfdasfasdfasdd";
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qrcode_teacher, container, false);
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(texto, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrcodeView = view.findViewById(R.id.qrcodeView);
            qrcodeView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return view;
    }


}
