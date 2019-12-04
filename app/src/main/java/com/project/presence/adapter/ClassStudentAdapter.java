package com.project.presence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;
import com.project.presence.adapter.Holder.ClassStudentViewHolder;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

public class ClassStudentAdapter extends RecyclerView.Adapter <ClassStudentViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public ClassStudentAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public ClassStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_student_class_list_all_subjects,
                parent,
                false
        );
        return new ClassStudentViewHolder(raiz);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassStudentViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.student_class_subjectTV.setText(schoolSubject.getSchoolSubject());
        holder.student_class_dayTV.setText(schoolSubject.getSchedule());
        holder.student_class_studentAmmountTV.setText(schoolSubject.getStudentAmmount().toString() + "alunos");
        holder.student_class_themeTV.setText(schoolSubject.getSchoolTheme());
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
