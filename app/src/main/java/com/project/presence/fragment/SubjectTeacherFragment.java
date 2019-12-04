package com.project.presence.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
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
import com.project.presence.adapter.RecyclerItemClickListener;
import com.project.presence.adapter.SubjectStudentAdapter;
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
public class SubjectTeacherFragment extends Fragment {

    private TextView subjectTV;
    private TextView currentdateTV;
    private Button changeDateBtn;

    private RecyclerView subjectTeacherRecyclerView;
    private RecyclerView subjectTeacherRecyclerView2;
    private SubjectTeacherAdapter adapter;
    private SubjectTeacherAdapter2 adapter2;
    private List<StudentClass> studentClasses;
    private RelativeLayout calendarRL;
    private List<SchoolSubjectAdapterModel> schoolSubjects;
    private SchoolSubject schoolSubject;

    private RequestQueue requestQueue;

    private Bundle bundle = new Bundle();

    public SubjectTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_subject_teacher, container, false);
        final User user = (User) getArguments().getSerializable("user");
        System.out.println("Entrou em uma matéria");
        if(schoolSubject==null) {
            List<SchoolSubjectAdapterModel> schoolSubjectsTeste = getArguments().getParcelable("teste");
            SchoolSubjectAdapterModel schoolSubjectTeste = (SchoolSubjectAdapterModel) getArguments().getSerializable("schoolSubject");

            System.out.println("Matéria: " + schoolSubjectTeste);
            System.out.println("SchooSubject no inicio" + schoolSubjectTeste + "\nSchoolSubjects no inicio" + schoolSubjectsTeste);
        }

        studentClasses = new ArrayList<>();

        try {
            studentClasses = new StudentClassListService().execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        schoolSubjects = new Converter().studentClassToSubjectAdapter(studentClasses, user);

        System.out.println("SchoolSubjects gerada: " + schoolSubjects);

        subjectTV = view.findViewById(R.id.teacher_subject_subjectTextView);
        subjectTV.setText("Materia"); //TODO pegar o nome da matéria selecionada anteriormente

        String date = getArguments().getString("date");

        if (date==null && currentdateTV==null) {
            currentdateTV = view.findViewById(R.id.teacher_subject_currentDateTextView);
            Date now = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = formater.format(now);
            currentdateTV.setText(currentDate);
        }
        else if (date!=null) {
            currentdateTV = view.findViewById(R.id.teacher_subject_currentDateTextView);
            currentdateTV.setText(date);
        }

        changeDateBtn = view.findViewById(R.id.teacher_subject_changeDateButton);
        changeDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Quando mudar a data, atualizar a lista de alunos para aquela data
                calendarRL = view.findViewById(R.id.teacher_subject_calendarRelativeLayout);
                calendarRL.setVisibility(View.VISIBLE);

                CalendarView calendarView = view.findViewById(R.id.teacher_subject_dateCalendarView);

                calendarView.setOnDateChangeListener(
                        new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                String date = dayOfMonth + "/" + month + "/" + year;

                                Bundle dateBundle = new Bundle();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                SubjectTeacherFragment subjectTeacherFragment = new SubjectTeacherFragment();
                                dateBundle.putSerializable("date", date);
                                dateBundle.putSerializable("user", user);
                                subjectTeacherFragment.setArguments(dateBundle);
                                fragmentTransaction.replace(R.id.viewPagerTeacher, subjectTeacherFragment).commit();

                                calendarRL.setVisibility(View.GONE);
                            }
                        }
                );
            }
        });

        subjectTeacherRecyclerView = view.findViewById(R.id.teacher_subject_daySubjectRecyclerView);
        subjectTeacherRecyclerView2 = view.findViewById(R.id.teacher_subject_daySubjectDataRecyclerView);




        subjectTeacherRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), subjectTeacherRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println("Escolheu um aluno");
                        schoolSubjects.get(position).getSubjectId();
                        Intent intent = new Intent(getContext(), MissStudentFragment.class);
                        intent.putExtra("user", user);
                        intent.putExtra("schoolSubject", schoolSubjects.get(position).getSubjectId());

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        CurrentStudentTeacherFragment currentStudentTeacherFragment = new CurrentStudentTeacherFragment();
                        bundle.putSerializable("user", user);
                        bundle.putSerializable("schoolSubjects", schoolSubjects.get(position).getSubjectId());
                        bundle.putSerializable("student", schoolSubjects.get(position).getStudent());
                        currentStudentTeacherFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.viewPagerTeacher, currentStudentTeacherFragment).commit();
                        System.out.println("Aluno: " + schoolSubjects.get(position).getStudent());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );


        adapter = new SubjectTeacherAdapter(this.getContext(), schoolSubjects);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        subjectTeacherRecyclerView.setAdapter(adapter);
        subjectTeacherRecyclerView.setLayoutManager(llm);

        adapter2 = new SubjectTeacherAdapter2(this.getContext(), schoolSubjects);
        LinearLayoutManager llm2 = new LinearLayoutManager(this.getContext());
        subjectTeacherRecyclerView2.setAdapter(adapter2);
        subjectTeacherRecyclerView2.setLayoutManager(llm2);

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
