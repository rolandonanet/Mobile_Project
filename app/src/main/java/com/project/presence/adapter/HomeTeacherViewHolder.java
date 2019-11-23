package com.project.presence.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class HomeTeacherViewHolder extends RecyclerView.ViewHolder {
    public TextView teacher_subjectTV;
    public TextView teacher_clasroomTV;
    public TextView teacher_studentAmmountTV;
    public TextView teacher_themeTV;

    public HomeTeacherViewHolder (View itemView) {
        super (itemView);
        this.teacher_subjectTV = itemView.findViewById(R.id.teacher_subjectTextView);
        this.teacher_clasroomTV = itemView.findViewById(R.id.teacher_classroomTextView);
        this.teacher_studentAmmountTV = itemView.findViewById(R.id.teacher_studentAmmountTextView);
        this.teacher_themeTV = itemView.findViewById(R.id.teacher_themeTextView);
    }
}
