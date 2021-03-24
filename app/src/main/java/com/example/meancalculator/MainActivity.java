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

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText lastName;
    private EditText grades;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameInput);
        lastName = findViewById(R.id.lastNameInput);
        grades = findViewById(R.id.gradesInput);

        button = findViewById(R.id.button);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(name)) {
                    Toast.makeText(MainActivity.this, R.string.nameWarning, Toast.LENGTH_SHORT).show();
                    name.setError("Imię nie może być puste");
                }
            }
        });

        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(lastName)) {
                    Toast.makeText(MainActivity.this, R.string.lastNameWarning, Toast.LENGTH_SHORT).show();
                    lastName.setError("Nazwisko nie może być puste");
                }
            }
        });

        grades.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (isEmpty(grades)) {
                        Toast.makeText(MainActivity.this, R.string.gradesWarning, Toast.LENGTH_SHORT).show();
                        grades.setError("Pole oceny nie może być puste");
                    } else if (!inNumbersSet(grades)) {
                        Toast.makeText(MainActivity.this, R.string.gradesNumberWarning, Toast.LENGTH_SHORT).show();
                        grades.setError("Podaj liczbę ocen w przedziale od 5 do 15");
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
        outState.putInt("grades", Integer.parseInt(grades.getText().toString()));

        super.onSaveInstanceState(outState);
    }
}