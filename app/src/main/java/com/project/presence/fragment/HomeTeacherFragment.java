package com.project.presence.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.project.presence.R;
import com.project.presence.adapter.HomeTeacherAdapter;
import com.project.presence.adapter.RecyclerItemClickListener;
import com.project.presence.converter.Converter;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;
import com.project.presence.service.StudentClassListService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTeacherFragment extends Fragment {

    private TextView registrationTV, teacherDateTV;
    private RecyclerView homeTeacherRecyclerView;
    private HomeTeacherAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    private RequestQueue requestQueue;

    private Bundle bundle = new Bundle();

    public HomeTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_teacher, container, false);
        final User user = (User) getArguments().getSerializable("user");

//        PROFESSOR

//        QRCodeDTO qrCode = new QRCodeDTO();
//
//        qrCode.setTeacherId(user.get_id());
//
//        QRCodeResponseDTO generatedQRCode = new QRCodeResponseDTO();
//
//        try {
//            generatedQRCode = new GenerateQRCodeService().execute(qrCode).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        ALUNO
//
//        QRCodeCheckDTO qrcodeCheck = new QRCodeCheckDTO();
//
//        qrcodeCheck.setEncodedQRCode(generatedQRCode.getMessage());
//
//        qrcodeCheck.setStudentId(user.get_id());
//
//        QRCodeResponseDTO studentQRCodeCheck = new QRCodeResponseDTO();
//
//        try {
//            studentQRCodeCheck = new CheckQRCodeService().execute(qrcodeCheck).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        registrationTV = view.findViewById(R.id.teacher_home_registration_viewTextView);
        registrationTV.setText("Matricula: " + user.getRegistration().toString());

        System.out.println("Matérias do user: " + user.getSchedules());

        teacherDateTV = view.findViewById(R.id.teacher_home_date_viewTextView);
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formater.format(now);
//        teacherDataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        teacherDateTV.setText(currentDate);

        homeTeacherRecyclerView = view.findViewById(R.id.teacher_home_currentSubjectsRecyclerView);

        studentClasses = new ArrayList<>();

        try {
            studentClasses = new StudentClassListService().execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        schoolSubjects = new Converter().studentClassToSubjectAdapter(studentClasses, user);

        System.out.println("Stalling: " + schoolSubjects);


        homeTeacherRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), homeTeacherRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println("Entrou aqui");
                        schoolSubjects.get(position).getSubjectId();
                        Intent intent = new Intent(getContext(), MissTeacherFragment.class);
                        intent.putExtra("user", user);
                        intent.putExtra("schoolSubject", schoolSubjects.get(position).getSubjectId());

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        MissTeacherFragment missTeacherFragment = new MissTeacherFragment();
                        bundle.putSerializable("user", user);
                        bundle.putSerializable("schoolSubjects", schoolSubjects.get(position).getSubjectId());
                        missTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, missTeacherFragment).commit();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        adapter = new HomeTeacherAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        homeTeacherRecyclerView.setAdapter(adapter);
        homeTeacherRecyclerView.setLayoutManager(llm);

        requestQueue = Volley.newRequestQueue(this.getContext());

        Intent intent = new Intent();
        intent.putExtra("user", user);
        return view;
    }

}
