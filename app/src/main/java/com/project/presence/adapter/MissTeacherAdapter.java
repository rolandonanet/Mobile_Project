package com.project.presence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;
import com.project.presence.adapter.Holder.MissTeacherViewHolder;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

public class MissTeacherAdapter extends RecyclerView.Adapter <MissTeacherViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public MissTeacherAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public MissTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_teacher_miss_list_student,
                parent,
                false
        );
        return new MissTeacherViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull MissTeacherViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.teacher_miss_studentTV.setText("Rafael Silva Sanches");//String.valueOf(schoolSubject.getStudents()));
//        holder.teacher_miss_list_studentGL.setBackgroundColor(222);
        holder.teacher_miss_presenceBtn.setText("Presente");
        holder.teacher_miss_abssentBtn.setText("Falta");
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
