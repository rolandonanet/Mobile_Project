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
import com.project.presence.adapter.HomeStudentAdapter;
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
public class HomeStudentFragment extends Fragment {

    private TextView registrationTV, studentDateTV;
    private RecyclerView homeStudentRecyclerView;
    private HomeStudentAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    private RequestQueue requestQueue;

    private Bundle bundle = new Bundle();

    public HomeStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_student, container, false);
        final User user = (User) getArguments().getSerializable("user");

        registrationTV = view.findViewById(R.id.student_home_registration_viewTextView);
        registrationTV.setText("RA: " + user.getRegistration().toString());

        System.out.println("Matérias do user: " + user.getSchedules());

        studentDateTV = view.findViewById(R.id.student_home_date_viewTextView);
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formater.format(now);
//        teacherDataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        studentDateTV.setText(currentDate);

        homeStudentRecyclerView = view.findViewById(R.id.student_home_currentSubjectsRecyclerView);

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


        homeStudentRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), homeStudentRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println("Clicou em uma matéria: ");
                        schoolSubjects.get(position).getSubjectId();

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        SubjectStudentFragment subjectStudentFragment = new SubjectStudentFragment();

                        bundle.putSerializable("user", user);
                        bundle.putSerializable("schoolSubject", schoolSubjects.get(position).getSubjectId());
                        subjectStudentFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerStudent, subjectStudentFragment).commit();
                        System.out.println(schoolSubjects.get(position).getSubjectId());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        adapter = new HomeStudentAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        homeStudentRecyclerView.setAdapter(adapter);
        homeStudentRecyclerView.setLayoutManager(llm);

        requestQueue = Volley.newRequestQueue(this.getContext());

        Intent intent = new Intent();
        intent.putExtra("user", user);

        return view;
    }

//    @Override
//    public void onBackPressed(){
//        finish();
//        System.exit(0);
//    }

}
