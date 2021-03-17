package com.example.meancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.nameInput);
        EditText lastName = findViewById(R.id.lastNameInput);
        EditText grades = findViewById(R.id.gradesInput);

        Button button = findViewById(R.id.button);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (isEmpty(name)) {
                        Toast.makeText(MainActivity.this, R.string.nameWarning, Toast.LENGTH_SHORT).show();
                        name.setError("Imię nie może być puste");
                    }
//                    else if (!isEmpty(lastName) && !isEmpty(grades) && inNumbersSet(grades))
//                        button.setVisibility(Button.VISIBLE);
                }
            }
        });

        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (isEmpty(lastName)) {
                        Toast.makeText(MainActivity.this, R.string.lastNameWarning, Toast.LENGTH_SHORT).show();
                        lastName.setError("Nazwisko nie może być puste");
                    }
//                    else if (!isEmpty(grades) && inNumbersSet(grades))
//                        button.setVisibility(Button.VISIBLE);
                }
            }
        });

        grades.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (isEmpty(grades)) {
                        Toast.makeText(MainActivity.this, R.string.gradesWarning, Toast.LENGTH_SHORT).show();
                        grades.setError("Pole oceny nie może być puste");
                    }
                    else if (!inNumbersSet(grades)) {
                        Toast.makeText(MainActivity.this, R.string.gradesNumberWarning, Toast.LENGTH_SHORT).show();
                        grades.setError("Podaj liczbę ocen w przedziale od 5 do 15");
                    }
//                    else if (!isEmpty(name) && !isEmpty(lastName) && !isEmpty(grades) && inNumbersSet(grades) )
//                        button.setVisibility(Button.VISIBLE);
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(lastName) && !isEmpty(grades) && inNumbersSet(grades))
                        button.setVisibility(Button.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(grades) && inNumbersSet(grades))
                        button.setVisibility(Button.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        grades.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(name) && !isEmpty(lastName) && !isEmpty(grades) && inNumbersSet(grades) )
                    button.setVisibility(Button.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public boolean inNumbersSet(EditText editText) {
        return Integer.parseInt(editText.getText().toString()) >= 5 && Integer.parseInt(editText.getText().toString()) <= 15;
    }
}