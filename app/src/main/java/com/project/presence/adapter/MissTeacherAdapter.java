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

public class HomeTeacherAdapter extends RecyclerView.Adapter <HomeTeacherViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public HomeTeacherAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public HomeTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.teacher_list_subject,
                parent,
                false
        );
        return new HomeTeacherViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull HomeTeacherViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.teacher_timeTV.setText(schoolSubject.getSchedule().toString());
        holder.teacher_subjectTV.setText(schoolSubject.getSchoolSubject());
        holder.teacher_clasroomTV.setText(schoolSubject.getClassroom());
        holder.teacher_studentAmmountTV.setText(schoolSubject.getStudentAmmount().toString() + "alunos");
        holder.teacher_themeTV.setText(schoolSubject.getSchoolTheme());
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
