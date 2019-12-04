package com.project.presence.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
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
import com.project.presence.model.SchoolSubject;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;
import com.project.presence.service.StudentClassListService;

import java.io.Serializable;
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
        System.out.println("Usuário Professor: " + user);

        String registration = "Matrícula: " + user.getRegistration();
        registrationTV = view.findViewById(R.id.teacher_home_registration_viewTextView);
        registrationTV.setText(registration);

        System.out.println("Matérias do Professor: " + user.getSchedules());
        System.out.println("Matrícula do Professor: " + user.getRegistration());

        teacherDateTV = view.findViewById(R.id.teacher_home_date_viewTextView);
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formater.format(now);
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

        System.out.println("SchoolSubjects: " + schoolSubjects);

        homeTeacherRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), homeTeacherRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println("Clicou em uma matéria: ");
                        schoolSubjects.get(position).getSubjectId();

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        SubjectTeacherFragment subjectTeacherFragment = new SubjectTeacherFragment();

                        bundle.putSerializable("user", user);
                        bundle.putSerializable("schoolSubject", schoolSubjects.get(position));
                        subjectTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, subjectTeacherFragment).commit();
                        System.out.println("Matéria escolhida: " + schoolSubjects.get(position).getSubjectId());
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

        Intent intent = new Intent();
        intent.putExtra("user", user);
        return view;
    }

}
