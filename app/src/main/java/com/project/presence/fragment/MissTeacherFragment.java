package com.project.presence.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.project.presence.R;
import com.project.presence.adapter.MissTeacherAdapter;
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
public class MissTeacherFragment extends Fragment {

    private TextView teacherMissDateTV;
    private TextView teacherMissSubjectTV;
    private RecyclerView missTeacherRecyclerView;
    private MissTeacherAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects;
    private SchoolSubjectAdapterModel schoolSubject;
    private Bundle bundle = new Bundle();

    private RequestQueue requestQueue;

    public MissTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_miss_teacher, container, false);
        final User user = (User) getArguments().getSerializable("user");

        teacherMissSubjectTV = view.findViewById(R.id.teacher_miss_subject_viewTextView);
        teacherMissDateTV = view.findViewById(R.id.teacher_miss_date_viewTextView);
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formater.format(now);
//        teacherDataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        teacherMissDateTV.setText(currentDate);

        missTeacherRecyclerView = view.findViewById(R.id.teacher_miss_listStudentRecyclerView);

        studentClasses = new ArrayList<>();

        try {
            studentClasses = new StudentClassListService().execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        schoolSubjects = new Converter().studentClassToSubjectAdapter(studentClasses, user);

//        teacherMissSubjectTV.setText("Matéria: " + schoolSubjects);

        System.out.println("Matéria: " + user.getSchedules());

        missTeacherRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), missTeacherRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        System.out.println("Entrou aqui");
//                        schoolSubjects.get(position).getSubjectId();
//                        Intent intent = new Intent(getContext(), MissTeacherFragment.class);
//                        intent.putExtra("user", user);
//                        bundle.putSerializable("user", user);
//
//                        FragmentManager fragmentManager = getFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        MissTeacherFragment missTeacherFragment = new MissTeacherFragment();
//                        bundle.putSerializable("user", user);
//                        missTeacherFragment.setArguments(bundle);
//                        fragmentTransaction.replace(R.id.viewPagerTeacher, missTeacherFragment).commit();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        adapter = new MissTeacherAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        missTeacherRecyclerView.setAdapter(adapter);
        missTeacherRecyclerView.setLayoutManager(llm);

        requestQueue = Volley.newRequestQueue(this.getContext());

        Intent intent = new Intent();
        intent.putExtra("user", user);
        return view;
    }

}
