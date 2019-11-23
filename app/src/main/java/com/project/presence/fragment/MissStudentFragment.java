package com.project.presence.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.presence.R;
import com.project.presence.adapter.HomeTeacherAdapter;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissStudentFragment extends Fragment {

    private RecyclerView missTeacherRecyclerView;
    private HomeTeacherAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    public MissStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_miss_teacher, container, false);
        User user = (User) getArguments().getSerializable("user");

        missTeacherRecyclerView = view.findViewById(R.id.listSubjectsRecyclerView);
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
//
//        adapter = new HomeTeacherAdapter(this.getContext(), schoolSubjects);
//        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
//        missTeacherRecyclerView.setAdapter(adapter);
//        missTeacherRecyclerView.setLayoutManager(llm);
        return view;
    }

}
