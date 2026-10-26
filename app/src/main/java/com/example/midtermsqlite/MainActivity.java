package com.example.midtermsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextStudentName;
    EditText editTextStudentID;

    Spinner spinnerMajor;

    Button btnAdd;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextStudentName =
                findViewById(R.id.editTextStudentName);

        editTextStudentID =
                findViewById(R.id.editTextStudentID);

        spinnerMajor =
                findViewById(R.id.spinnerMajor);

        btnAdd =
                findViewById(R.id.btnAdd);

        btnView =
                findViewById(R.id.btnView);

        String[] majors = {
                "Informatics",
                "Information Systems"
        };

        ArrayAdapter<String> majorAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        majors
                );

        majorAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerMajor.setAdapter(majorAdapter);

        btnAdd.setOnClickListener(view -> {

            String studentName =
                    editTextStudentName.getText().toString();

            String studentID =
                    editTextStudentID.getText().toString();

            String major =
                    spinnerMajor.getSelectedItem().toString();

            if (studentName.length() <= 0 ||
                    studentID.length() <= 0) {

                Toast.makeText(
                        MainActivity.this,
                        "Enter data",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                DBHelper dbHelper =
                        new DBHelper(MainActivity.this);

                StudentModel studentModel =
                        new StudentModel(
                                studentName,
                                studentID,
                                major
                        );

                dbHelper.addStudent(studentModel);

                Toast.makeText(
                        MainActivity.this,
                        "Add Successful",
                        Toast.LENGTH_SHORT
                ).show();

                editTextStudentName.setText("");
                editTextStudentID.setText("");
            }
        });

        btnView.setOnClickListener(view -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            ViewStudent.class
                    );

            startActivity(intent);
        });
    }
}