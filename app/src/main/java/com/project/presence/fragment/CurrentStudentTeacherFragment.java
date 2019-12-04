package com.project.presence.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.project.presence.R;
import com.project.presence.adapter.CurrentStudentTeacherAdapter;
import com.project.presence.adapter.RecyclerItemClickListener;
import com.project.presence.adapter.SubjectTeacherAdapter;
import com.project.presence.adapter.SubjectTeacherAdapter2;
import com.project.presence.converter.Converter;
import com.project.presence.model.SchoolSubject;
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
public class CurrentStudentTeacherFragment extends Fragment {

    private TextView studentTV;
    private Button currentSubjectBtn;
    private Button changeDateBtn;
    private RelativeLayout calendarRL;
    private GridLayout currentDateGridLayout;

    private RecyclerView currentStudentRecyclerView;
    private CurrentStudentTeacherAdapter adapter;
    private List<StudentClass> studentClasses;
    private List<SchoolSubjectAdapterModel> schoolSubjects;
    private SchoolSubject schoolSubject;

    private RequestQueue requestQueue;

    private Bundle bundle = new Bundle();

    public CurrentStudentTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_current_student_teacher, container, false);
        final User user = (User) getArguments().getSerializable("user");

        studentClasses = new ArrayList<>();

        try {
            studentClasses = new StudentClassListService().execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        schoolSubjects = new Converter().studentClassToSubjectAdapter(studentClasses, user);

//        System.out.println("Stalling: " + schoolSubjects);

        studentTV = view.findViewById(R.id.teacher_currentStudent_nameTextView);
        studentTV.setText("Gabriel Roland"); //TODO pegar nome do aluno selecionado

        String date = getArguments().getString("date");
//        changeDateBtn = view.findViewById(R.id.teacher_currentStudent_dateButton);

        if (date==null && changeDateBtn==null) {
            changeDateBtn = view.findViewById(R.id.teacher_currentStudent_dateButton);
            Date now = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = formater.format(now);
//        teacherDataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            changeDateBtn.setText(currentDate);
        }
        else if (date!=null) {
            changeDateBtn = view.findViewById(R.id.teacher_currentStudent_dateButton);
            changeDateBtn.setText(date);
        }

        changeDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                calendarRL = view.findViewById(R.id.teacher_currentStudent_calendarRelativeLayout);
                calendarRL.setVisibility(View.VISIBLE);

                CalendarView calendarView = view.findViewById(R.id.teacher_currentStudent_dateCalendarView);

                calendarView.setOnDateChangeListener(
                        new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                String date = dayOfMonth + "/" + month + "/" + year;

                                Bundle dateBundle = new Bundle();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                CurrentStudentTeacherFragment currentStudentTeacherFragment = new CurrentStudentTeacherFragment();
                                dateBundle.putSerializable("date", date);
                                dateBundle.putSerializable("user", user);
                                currentStudentTeacherFragment.setArguments(dateBundle);
                                fragmentTransaction.replace(R.id.viewPagerTeacher, currentStudentTeacherFragment).commit();

                                calendarRL.setVisibility(View.GONE);
                            }
                        }
                );
            }
        });

        currentStudentRecyclerView = view.findViewById(R.id.teacher_currentStudent_detailsRecyclerView);

        /*
        currentStudentRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), currentStudentRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
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

        adapter = new CurrentStudentTeacherAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        currentStudentRecyclerView.setAdapter(adapter);
        currentStudentRecyclerView.setLayoutManager(llm);

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
