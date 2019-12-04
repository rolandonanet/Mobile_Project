package com.project.presence.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class SubjectTeacherViewHolder extends RecyclerView.ViewHolder {
    public TextView teacher_subjectGrid_numberRATextView;
    public TextView teacher_subjectGrid_studentNameTextView;
    public TextView teacher_subjectGrid_studentAbssentTextView;
    public Button teacher_subjectGrid_abssentButton;
    public Button teacher_subjectGrid_presenceButton;

    public TextView teacher_subjectGrid_classroomTextView;
    public TextView teacher_subjectGrid_studentAmmountTextView;
    public TextView teacher_subject_themeTextView;

    public SubjectTeacherViewHolder(View itemView) {
        super (itemView);
        this.teacher_subjectGrid_numberRATextView = itemView.findViewById(R.id.teacher_subjectGrid_numberRATextView);
        this.teacher_subjectGrid_studentNameTextView = itemView.findViewById(R.id.teacher_subjectGrid_studentNameTextView);
        this.teacher_subjectGrid_studentAbssentTextView = itemView.findViewById(R.id.teacher_subjectGrid_studentAbssentTextView);
        this.teacher_subjectGrid_abssentButton = itemView.findViewById(R.id.teacher_subjectGrid_abssentButton);
        this.teacher_subjectGrid_presenceButton = itemView.findViewById(R.id.teacher_subjectGrid_presenceButton);

        this.teacher_subjectGrid_classroomTextView = itemView.findViewById(R.id.teacher_subjectGrid_classroomTextView);
        this.teacher_subjectGrid_studentAmmountTextView = itemView.findViewById(R.id.teacher_subjectGrid_studentAmmountTextView);
        this.teacher_subject_themeTextView = itemView.findViewById(R.id.teacher_subject_themeTextView);
    }
}
