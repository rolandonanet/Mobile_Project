package com.project.presence.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


    public HomeTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_teacher, container, false);
        User user = (User) getArguments().getSerializable("user");
        registrationTV = view.findViewById(R.id.teacher_registrationTextView);
        registrationTV.setText("Matricula: " + user.getRegistration().toString());

        teacherDateTV = view.findViewById(R.id.teacher_dateTextView);
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formater.format(now);
//        teacherDataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        teacherDateTV.setText(currentDate);

        homeTeacherRecyclerView = view.findViewById(R.id.currentSubjectsRecyclerView);


//        studentClasses = new ArrayList<>();
//
//        try {
//            studentClasses = new StudentClassListService().execute("").get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        schoolSubjects = new Converter().studentClassToSubjectAdapter(studentClasses, user);
//
//        System.out.println("Stalling");
//        homeTeacherRecyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getContext(), homeTeacherRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        //CHAMARA VIEW PASSANDO ID DA MATÉRIA
//                        schoolSubjects.get(position).getSubjectId();
//                    }
//
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//
//                    }
//                })
//        );
//
//        adapter = new HomeTeacherAdapter(this.getContext(), schoolSubjects);
//        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
//        homeTeacherRecyclerView.setAdapter(adapter);
//        homeTeacherRecyclerView.setLayoutManager(llm);

        return view;
    }

}
