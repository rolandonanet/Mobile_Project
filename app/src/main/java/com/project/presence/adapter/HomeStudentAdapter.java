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

public class HomeStudentAdapter extends RecyclerView.Adapter <HomeStudentViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public HomeStudentAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public HomeStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_student_home_list_subject,
                parent,
                false
        );
        return new HomeStudentViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull HomeStudentViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.student_timeTV.setText(schoolSubject.getSchedule().toString());
        holder.student_subjectTV.setText(schoolSubject.getSchoolSubject());
        holder.student_clasroomTV.setText(schoolSubject.getClassroom());
        holder.student_studentAmmountTV.setText(schoolSubject.getStudentAmmount().toString() + "alunos");
        holder.student_themeTV.setText(schoolSubject.getSchoolTheme());
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
