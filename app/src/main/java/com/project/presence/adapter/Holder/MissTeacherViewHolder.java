package com.project.presence.adapter.Holder;

import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;

public class MissTeacherViewHolder extends RecyclerView.ViewHolder {
    public TextView teacher_miss_studentTV;
    public Button teacher_miss_presenceBtn, teacher_miss_abssentBtn;
    public RelativeLayout teacher_miss_list_studentGL;

    public MissTeacherViewHolder(View itemView) {
        super(itemView);
        this.teacher_miss_studentTV = itemView.findViewById(R.id.teacher_missGrid_studentTextView);
        this.teacher_miss_presenceBtn = itemView.findViewById(R.id.teacher_missGrid_presenceButton);
        this.teacher_miss_abssentBtn = itemView.findViewById(R.id.teacher_missGrid_abssentButton);
        this.teacher_miss_list_studentGL = itemView.findViewById(R.id.teacher_missGrid_listStudent);
    }
}
