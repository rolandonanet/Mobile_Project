package com.project.presence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;
import com.project.presence.adapter.Holder.CurrentStudentTeacherViewHolder;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

public class CurrentStudentTeacherAdapter extends RecyclerView.Adapter <CurrentStudentTeacherViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public CurrentStudentTeacherAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public CurrentStudentTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_teacher_current_student_details,
                parent,
                false
        );
        return new CurrentStudentTeacherViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull CurrentStudentTeacherViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
//        holder.teacher_currentStudentGrid_detailsGridLayout.setText("81722279");//schoolSubject.getSchedule().toString());
        holder.teacher_currentStudentGrid_dayTextView.setText("06");//schoolSubject.getSchoolSubject());
        holder.teacher_currentStudentGrid_weekDayTextView.setText("Quinta");//schoolSubject.getClassroom());
//        holder.teacher_currentStudentGrid_presenceButton.setText("Presença");//schoolSubject.getStudentAmmount().toString() + "alunos");
//        holder.teacher_currentStudentGrid_abssentButton.setText("i03-h");//schoolSubject.getStudentAmmount().toString() + "alunos");
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
