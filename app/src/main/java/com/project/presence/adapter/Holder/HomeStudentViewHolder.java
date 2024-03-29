package com.project.presence.adapter.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class HomeStudentViewHolder extends RecyclerView.ViewHolder {
    public TextView student_timeTV, student_subjectTV, student_clasroomTV, student_studentAmmountTV, student_themeTV;

    public HomeStudentViewHolder(View itemView) {
        super(itemView);
        this.student_timeTV = itemView.findViewById(R.id.student_homeGrid_timeTextView);
        this.student_subjectTV = itemView.findViewById(R.id.student_homeGrid_subjectTextView);
        this.student_clasroomTV = itemView.findViewById(R.id.student_homeGrid_classroomTextView);
        this.student_studentAmmountTV = itemView.findViewById(R.id.student_homeGrid_studentAmmountTextView);
        this.student_themeTV = itemView.findViewById(R.id.student_homeGrid_themeTextView);
    }
}
