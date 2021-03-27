package com.example.meancalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.meancalculator.R.string.gradesNumberWarning;
import static com.example.meancalculator.R.string.gradesWarning;
import static com.example.meancalculator.R.string.lastNameWarning;
import static com.example.meancalculator.R.string.nameWarning;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText lastName;
    private EditText grades;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_constraint);

        name = findViewById(R.id.nameInput);
        lastName = findViewById(R.id.lastNameInput);
        grades = findViewById(R.id.gradesInput);

        button = findViewById(R.id.button);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(name)) {
                    Toast.makeText(MainActivity.this, nameWarning, Toast.LENGTH_SHORT).show();
                    name.setError(getString(nameWarning));
                }
            }
        });

        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(lastName)) {
                    Toast.makeText(MainActivity.this, lastNameWarning, Toast.LENGTH_SHORT).show();
                    lastName.setError(getString(lastNameWarning));
                }
            }
        });

        grades.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (isEmpty(grades)) {
                        Toast.makeText(MainActivity.this, gradesWarning, Toast.LENGTH_SHORT).show();
                        grades.setError(getString(gradesWarning));
                    } else if (!inNumbersSet(grades)) {
                        Toast.makeText(MainActivity.this, gradesNumberWarning, Toast.LENGTH_SHORT).show();
                        grades.setError(getString(gradesNumberWarning));
                    }
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(lastName) && !isEmpty(grades) && inNumbersSet(grades))
                    button.setVisibility(Button.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(grades) && inNumbersSet(grades))
                    button.setVisibility(Button.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        grades.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(name) && !isEmpty(lastName) && !isEmpty(grades) && inNumbersSet(grades))
                    button.setVisibility(Button.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        if (savedInstanceState != null ) {
            String savedName = savedInstanceState.getString("name");
            String savedLastName = savedInstanceState.getString("lastName");
            String savedGradesName = savedInstanceState.getString("grades");

            name.setText(savedName);
            lastName.setText(savedLastName);
            grades.setText(savedGradesName);
        }
    }

    public boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public boolean inNumbersSet(EditText editText) {
        return Integer.parseInt(editText.getText().toString()) >= 5 && Integer.parseInt(editText.getText().toString()) <= 15;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // getText() returns CharSequence not String
        name = findViewById(R.id.nameInput);
        lastName = findViewById(R.id.lastNameInput);
        grades = findViewById(R.id.gradesInput);

        outState.putString("name", name.getText().toString());
        outState.putString("lastName", lastName.getText().toString());
        outState.putString("grades", grades.getText().toString());

        super.onSaveInstanceState(outState);
    }
}