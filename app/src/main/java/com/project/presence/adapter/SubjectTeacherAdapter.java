package com.project.presence.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.presence.R;
import com.project.presence.adapter.Holder.SubjectTeacherViewHolder;
import com.project.presence.model.adaptermodel.SchoolSubjectAdapterModel;

import java.util.List;

public class SubjectTeacherAdapter extends RecyclerView.Adapter <SubjectTeacherViewHolder> {
    private Context context;
    private List<SchoolSubjectAdapterModel> schoolSubjects;

    // construtor
    public SubjectTeacherAdapter(Context context, List<SchoolSubjectAdapterModel> schoolSubjects) {
        this.context = context;
        this.schoolSubjects = schoolSubjects;
    }

    public SubjectTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.grid_teacher_subject_current_subject,
                parent,
                false
        );
        return new SubjectTeacherViewHolder(raiz);
    }

    public void onBindViewHolder(@NonNull SubjectTeacherViewHolder holder, int position) {
        SchoolSubjectAdapterModel schoolSubject = this.schoolSubjects.get(position);
        holder.teacher_subjectGrid_numberRATextView.setText("81722279");                //  TODO Pegar o RA do estudante selecionado, provavelmente usando isso: schoolSubjects.get(position).getStudentRA()
        holder.teacher_subjectGrid_studentNameTextView.setText("Rafael Silva Sanches"); //  TODO Pegar o RA do estudante selecionado, provavelmente usando isso: schoolSubjects.get(position).getStudent()
        holder.teacher_subjectGrid_studentAbssentTextView.setText("Faltas: 4");         //  TODO Pegar o RA do estudante selecionado, faço nem ideia como

        holder.teacher_subjectGrid_abssentButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder mensagem = new AlertDialog.Builder(context);
                        mensagem.setTitle("Titulo");
                        mensagem.setMessage("Mensagem");
                        mensagem.setNeutralButton("Cancelar", null);
                        mensagem.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO Atribuir falta ao aluno
                                // ação a ser executada ao clicar no botão

                            }
                        });
                        mensagem.show();
                    }
                });
        holder.teacher_subjectGrid_presenceButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder mensagem = new AlertDialog.Builder(context);
                        mensagem.setTitle("Titulo");
                        mensagem.setMessage("Mensagem");
                        mensagem.setNeutralButton("Cancelar", null);
                        mensagem.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO Atribuir presença ao aluno
                                // ação a ser executada ao clicar no botão

                            }
                        });
                        mensagem.show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return schoolSubjects.size();
    }
}
