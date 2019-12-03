package com.project.presence.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.project.presence.R;
import com.project.presence.adapter.RecyclerItemClickListener;
import com.project.presence.adapter.SubjectStudentAdapter;
import com.project.presence.converter.Converter;
import com.project.presence.model.SchoolSubject;
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
public class SubjectStudentFragment extends Fragment {

    private TextView subjectTV;
    private TextView currentdateTV;
    private TextView classesAmmountTV;
    private TextView abssentAmmountTV;
    private TextView abssentPercentualTV;
    private Button changeDateBtn;

    private RecyclerView subjectStudentRecyclerView;
    private SubjectStudentAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects;
    private SchoolSubject schoolSubject;

    private RequestQueue requestQueue;

    private Bundle bundle = new Bundle();

    public SubjectStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject_student, container, false);
        final User user = (User) getArguments().getSerializable("user");

        System.out.println("Stalling: " + schoolSubjects);
        subjectTV = view.findViewById(R.id.student_subject_subjectTextView);
        subjectTV.setText("Materia"); //TODO

        changeDateBtn = view.findViewById(R.id.student_subject_changeDateButton);
        changeDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        currentdateTV = view.findViewById(R.id.student_subject_currentDateTextView);
        currentdateTV.setText(""); //TODO

        classesAmmountTV = view.findViewById(R.id.student_subject_classAmmountTextView);
        classesAmmountTV.setText("22"); //TODO

        abssentAmmountTV = view.findViewById(R.id.student_subject_abssentAmmountTextView);
        abssentAmmountTV.setText("4"); //TODO

        abssentPercentualTV = view.findViewById(R.id.student_subject_abssentPercentualTextView);
        abssentPercentualTV.setText("10% de falta"); //TODO

        /*
        studentDateTV = view.findViewById(R.id.student_home_date_viewTextView);
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formater.format(now);
//        teacherDataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        studentDateTV.setText(currentDate);
*/
        subjectStudentRecyclerView = view.findViewById(R.id.student_subject_weekSubjectRecyclerView);

        studentClasses = new ArrayList<>();

        try {
            studentClasses = new StudentClassListService().execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        schoolSubjects = new Converter().studentClassToSubjectAdapter(studentClasses, user);

/*
        subjectStudentRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), subjectStudentRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println("Entrou aqui");
                        schoolSubjects.get(position).getSubjectId();
                        Intent intent = new Intent(getContext(), MissStudentFragment.class);
                        intent.putExtra("user", user);
                        intent.putExtra("schoolSubject", schoolSubjects.get(position).getSubjectId());

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        MissStudentFragment missStudentFragment = new MissStudentFragment();
                        bundle.putSerializable("user", user);
                        bundle.putSerializable("schoolSubjects", schoolSubjects.get(position).getSubjectId());
                        missStudentFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerStudent, missStudentFragment).commit();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
 */

        adapter = new SubjectStudentAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        subjectStudentRecyclerView.setAdapter(adapter);
        subjectStudentRecyclerView.setLayoutManager(llm);

        requestQueue = Volley.newRequestQueue(this.getContext());

        Intent intent = new Intent();
        intent.putExtra("user", user);
        return view;
    }

    /*
    @Override
    public void onBackPressed(){
        finish();
        System.exit(0);
    }
    */

}
