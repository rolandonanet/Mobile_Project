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
import android.widget.Toast;

import com.project.presence.R;
import com.project.presence.adapter.ClassTeacherAdapter;
import com.project.presence.adapter.RecyclerItemClickListener;
import com.project.presence.converter.Converter;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;
import com.project.presence.service.StudentClassListService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassTeacherFragment extends Fragment {

    private RecyclerView classTeacherRecyclerView;
    private ClassTeacherAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects = new ArrayList<>();
    private Bundle bundle = new Bundle();


    public ClassTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_teacher, container, false);
        final User user = (User) getArguments().getSerializable("user");

        classTeacherRecyclerView = view.findViewById(R.id.teacher_class_listSubjectsRecyclerView);

        try {
            studentClasses = new StudentClassListService().execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        schoolSubjects = new Converter().studentClassToListSubjectAdapter(studentClasses, user);

        System.out.println("Stalling");

        adapter = new ClassTeacherAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        classTeacherRecyclerView.setAdapter(adapter);
        classTeacherRecyclerView.setLayoutManager(llm);

        Intent intent = new Intent(getContext(), MissTeacherFragment.class);
        intent.putExtra("user", user);
        bundle.putSerializable("user", user);
        classTeacherRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), classTeacherRecyclerView,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        SubjectTeacherFragment subjectTeacherFragment = new SubjectTeacherFragment();
                        bundle.putSerializable("user", user);
                        subjectTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, subjectTeacherFragment).commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(getContext(), R.string.visualize_subject, Toast.LENGTH_LONG).show();
                    }
                })
        );

        return view;
    }

}
