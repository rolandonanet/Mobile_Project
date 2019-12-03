package com.project.presence.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class SubjectStudentViewHolder extends RecyclerView.ViewHolder {
    public TextView student_subjectGrid_dayTextView;
    public TextView student_subjectGrid_weekDayTextView;
    public TextView student_subjectGrid_hourTextView;
    public TextView student_subjectGrid_classroomTextView;
    public TextView student_subjectGrid_themeTextView;

    public SubjectStudentViewHolder(View itemView) {
        super (itemView);
        this.student_subjectGrid_dayTextView = itemView.findViewById(R.id.student_subjectGrid_dayTextView);
        this.student_subjectGrid_weekDayTextView = itemView.findViewById(R.id.student_subjectGrid_weekDayTextView);
        this.student_subjectGrid_hourTextView = itemView.findViewById(R.id.student_subjectGrid_hourTextView);
        this.student_subjectGrid_classroomTextView = itemView.findViewById(R.id.student_subjectGrid_classroomTextView);
        this.student_subjectGrid_themeTextView = itemView.findViewById(R.id.student_subjectGrid_themeTextView);
    }
}
