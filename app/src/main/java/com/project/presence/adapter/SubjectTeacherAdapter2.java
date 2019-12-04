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

public class SubjectTeacherAdapter2 extends RecyclerView.Adapter <SubjectTeacherViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public SubjectTeacherAdapter2(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public SubjectTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_teacher_subject_current_subject_data,
                parent,
                false
        );
        return new SubjectTeacherViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull SubjectTeacherViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.teacher_subjectGrid_classroomTextView.setText("I03-H");//schoolSubject.getSchedule().toString());
        holder.teacher_subjectGrid_studentAmmountTextView.setText("40 Alunos");//schoolSubject.getSchoolSubject());
        holder.teacher_subject_themeTextView.setText("CCP3BN-MCB");//schoolSubject.getClassroom());
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
