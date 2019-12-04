package com.project.presence.adapter.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class HomeTeacherViewHolder extends RecyclerView.ViewHolder {
    public TextView teacher_timeTV, teacher_subjectTV, teacher_clasroomTV, teacher_studentAmmountTV, teacher_themeTV;

    public HomeTeacherViewHolder(View itemView) {
        super(itemView);
        this.teacher_timeTV = itemView.findViewById(R.id.teacher_homeGrid_timeTextView);
        this.teacher_subjectTV = itemView.findViewById(R.id.teacher_homeGrid_subjectTextView);
        this.teacher_clasroomTV = itemView.findViewById(R.id.teacher_homeGrid_classroomTextView);
        this.teacher_studentAmmountTV = itemView.findViewById(R.id.teacher_homeGrid_studentAmmountTextView);
        this.teacher_themeTV = itemView.findViewById(R.id.teacher_homeGrid_themeTextView);
    }
}
