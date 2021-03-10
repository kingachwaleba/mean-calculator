package com.example.meancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                if (!hasFocus && isEmpty(grades)) {
                    Toast.makeText(MainActivity.this, R.string.gradesWarning, Toast.LENGTH_SHORT).show();
                    grades.setError("Pole oceny nie może być puste");
                }
            }
        });
    }

    public boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}