package com.project.presence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

public class SubjectStudentAdapter extends RecyclerView.Adapter <SubjectStudentViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public SubjectStudentAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public SubjectStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_student_subject_week_subject,
                parent,
                false
        );
        return new SubjectStudentViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull SubjectStudentViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.student_subjectGrid_dayTextView.setText("5");//schoolSubject.getSchedule().toString());
        holder.student_subjectGrid_weekDayTextView.setText("Quarta");//schoolSubject.getSchoolSubject());
        holder.student_subjectGrid_hourTextView.setText("18:00");//schoolSubject.getClassroom());
        holder.student_subjectGrid_classroomTextView.setText("i03-h");//schoolSubject.getStudentAmmount().toString() + "alunos");
        holder.student_subjectGrid_themeTextView.setText("CCP3BN-MCB");//schoolSubject.getSchoolTheme());

    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
