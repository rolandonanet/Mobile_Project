package com.project.presence.adapter.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class ClassStudentViewHolder extends RecyclerView.ViewHolder {
    public TextView student_class_subjectTV, student_class_dayTV, student_class_studentAmmountTV, student_class_themeTV;

    public ClassStudentViewHolder(View itemView) {
        super(itemView);
        this.student_class_subjectTV = itemView.findViewById(R.id.student_classGrid_subjectTextView);
        this.student_class_dayTV = itemView.findViewById(R.id.student_classGrid_classroomTextView);
        this.student_class_studentAmmountTV = itemView.findViewById(R.id.student_classGrid_studentAmmountTextView);
        this.student_class_themeTV = itemView.findViewById(R.id.student_classGrid_themeTextView);
    }
}
