package com.project.presence.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class CurrentStudentTeacherViewHolder extends RecyclerView.ViewHolder {
    public GridLayout teacher_currentStudentGrid_detailsGridLayout;
    public TextView teacher_currentStudentGrid_dayTextView;
    public TextView teacher_currentStudentGrid_weekDayTextView;
    public Button teacher_currentStudentGrid_presenceButton;
    public Button teacher_currentStudentGrid_abssentButton;

    public CurrentStudentTeacherViewHolder(View itemView) {
        super (itemView);
        this.teacher_currentStudentGrid_detailsGridLayout = itemView.findViewById(R.id.teacher_currentStudentGrid_detailsGridLayout);
        this.teacher_currentStudentGrid_dayTextView = itemView.findViewById(R.id.teacher_currentStudentGrid_dayTextView);
        this.teacher_currentStudentGrid_weekDayTextView = itemView.findViewById(R.id.teacher_currentStudentGrid_weekDayTextView);
        this.teacher_currentStudentGrid_presenceButton = itemView.findViewById(R.id.teacher_currentStudentGrid_presenceButton);
        this.teacher_currentStudentGrid_abssentButton = itemView.findViewById(R.id.teacher_currentStudentGrid_abssentButton);
    }
}
