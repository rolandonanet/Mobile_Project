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

public class ClassTeacherAdapter extends RecyclerView.Adapter <ClassTeacherViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public ClassTeacherAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public ClassTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_teacher_class_list_all_subjects,
                parent,
                false
        );
        return new ClassTeacherViewHolder(raiz);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassTeacherViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.teacher_class_subjectTV.setText(schoolSubject.getSchoolSubject());
        holder.teacher_class_dayTV.setText(schoolSubject.getSchedule());
        holder.teacher_class_studentAmmountTV.setText(schoolSubject.getStudentAmmount().toString() + "alunos");
        holder.teacher_class_themeTV.setText(schoolSubject.getSchoolTheme());
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
