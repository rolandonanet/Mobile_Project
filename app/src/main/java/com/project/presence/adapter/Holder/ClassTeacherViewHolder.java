package com.project.presence.adapter.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class ClassTeacherViewHolder extends RecyclerView.ViewHolder {
    public TextView teacher_class_subjectTV, teacher_class_dayTV, teacher_class_studentAmmountTV, teacher_class_themeTV;

    public ClassTeacherViewHolder(View itemView) {
        super(itemView);
        this.teacher_class_subjectTV = itemView.findViewById(R.id.teacher_classGrid_subjectTextView);
        this.teacher_class_dayTV = itemView.findViewById(R.id.teacher_classGrid_classroomTextView);
        this.teacher_class_studentAmmountTV = itemView.findViewById(R.id.teacher_classGrid_studentAmmountTextView);
        this.teacher_class_themeTV = itemView.findViewById(R.id.teacher_classGrid_themeTextView);
    }
}
