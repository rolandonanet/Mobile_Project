package com.project.presence.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.presence.R;
import com.project.presence.adapter.HomeTeacherAdapter;
import com.project.presence.model.StudentClass;
import com.project.presence.model.User;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissStudentFragment extends Fragment {

    public MissStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_miss_student, container, false);

    }

}
