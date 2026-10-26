package com.example.midtermsqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter
        extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<StudentModel> studentModels;
    private Context context;
    private DBHelper dbHelper;

    public StudentAdapter(
            List<StudentModel> studentModels,
            Context context) {

        this.studentModels = studentModels;
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.student_item_list,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        StudentModel student =
                studentModels.get(position);

        holder.textViewID.setText(
                String.valueOf(student.getId())
        );

        holder.editTextStudentName.setText(
                student.getStudentName()
        );

        holder.editTextStudentID.setText(
                student.getStudentID()
        );

        String[] majors = {
                "Informatics",
                "Information Systems"
        };

        ArrayAdapter<String> majorAdapter =
                new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_spinner_item,
                        majors
                );

        majorAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        holder.spinnerMajor.setAdapter(majorAdapter);

        if (student.getMajor().equals("Information Systems")) {
            holder.spinnerMajor.setSelection(1);
        } else {
            holder.spinnerMajor.setSelection(0);
        }

        holder.btnEdit.setOnClickListener(view -> {

            String studentName =
                    holder.editTextStudentName
                            .getText()
                            .toString();

            String studentID =
                    holder.editTextStudentID
                            .getText()
                            .toString();

            String major =
                    holder.spinnerMajor
                            .getSelectedItem()
                            .toString();

            StudentModel updatedStudent =
                    new StudentModel(
                            student.getId(),
                            studentName,
                            studentID,
                            major
                    );

            dbHelper.updateStudent(updatedStudent);

            Intent intent =
                    new Intent(
                            context,
                            ViewStudent.class
                    );

            context.startActivity(intent);

            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });

        holder.btnDelete.setOnClickListener(view -> {

            dbHelper.deleteStudent(
                    student.getId()
            );

            studentModels.remove(position);

            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return studentModels.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView textViewID;

        EditText editTextStudentName;
        EditText editTextStudentID;

        Spinner spinnerMajor;

        Button btnEdit;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            textViewID =
                    itemView.findViewById(
                            R.id.textViewID
                    );

            editTextStudentName =
                    itemView.findViewById(
                            R.id.editTextStudentName
                    );

            editTextStudentID =
                    itemView.findViewById(
                            R.id.editTextStudentID
                    );

            spinnerMajor =
                    itemView.findViewById(
                            R.id.spinnerMajor
                    );

            btnEdit =
                    itemView.findViewById(
                            R.id.btnEdit
                    );

            btnDelete =
                    itemView.findViewById(
                            R.id.btnDelete
                    );
        }
    }
}