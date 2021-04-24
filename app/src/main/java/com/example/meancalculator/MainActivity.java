package com.example.meancalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.meancalculator.R.string.gradesNumberWarning;
import static com.example.meancalculator.R.string.gradesWarning;
import static com.example.meancalculator.R.string.lastNameWarning;
import static com.example.meancalculator.R.string.nameWarning;
import static com.example.meancalculator.R.string.successMessage;
import static com.example.meancalculator.R.string.successExitMessage;
import static com.example.meancalculator.R.string.failureMessage;
import static com.example.meancalculator.R.string.failureExitMessage;
import static com.example.meancalculator.R.string.meanMessage;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText lastName;
    private EditText grades;

    private TextView meanField;

    private Double mean = 0.0;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_constraint);

        name = findViewById(R.id.nameInput);
        lastName = findViewById(R.id.lastNameInput);
        grades = findViewById(R.id.gradesInput);

        meanField = findViewById(R.id.meanField);

        button = findViewById(R.id.button);

        button.setOnClickListener(v -> gradesActivity());

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
            String savedMean = savedInstanceState.getString("meanField");
            mean = savedInstanceState.getDouble("calculatedMean");

            name.setText(savedName);
            lastName.setText(savedLastName);
            grades.setText(savedGradesName);
            meanField.setVisibility(View.VISIBLE);
            meanField.setText(savedMean);

            showMessage();
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
        meanField = findViewById(R.id.meanField);

        outState.putString("name", name.getText().toString());
        outState.putString("lastName", lastName.getText().toString());
        outState.putString("grades", grades.getText().toString());
        outState.putString("meanField", meanField.getText().toString());
        outState.putDouble("calculatedMean", mean);

        super.onSaveInstanceState(outState);
    }

    protected void onActivityResult(int taskCode, int exitCode, Intent result) {
        super.onActivityResult(taskCode, exitCode, result);

        if (exitCode == RESULT_OK) {
            Bundle bundle = result.getExtras();
            mean = bundle.getDouble("srednia");

            String text = getString(meanMessage);

            // Set precision of mean using BigDecimal
            // Precision cannot be set using Double
            // Because floating-point values don't have decimal digits
            // They have binary digits
            Double truncatedDouble = BigDecimal.valueOf(mean).setScale(2, RoundingMode.HALF_UP).doubleValue();

            text = text.concat(" ").concat(String.valueOf(truncatedDouble));

            meanField = findViewById(R.id.meanField);
            meanField.setText(text);
            meanField.setVisibility(View.VISIBLE);

            showMessage();
        }
    }

    // Invoke one activity into another
    public void gradesActivity() {
        Intent intent = new Intent(MainActivity.this, GradesActivity.class);
        intent.putExtra("gradesInput", Integer.valueOf(Integer.parseInt(grades.getText().toString())));
        startActivityForResult(intent, 0);
    }

    public void showMessage() {
        button = findViewById(R.id.button);

        if (mean >= 3.0) {
            name.setEnabled(false);
            lastName.setEnabled(false);
            grades.setEnabled(false);

            button.setText(successMessage);
            button.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, successExitMessage, Toast.LENGTH_SHORT).show();
                finish();
            });
        }
        else if (mean >= 2.0) {
            name.setEnabled(false);
            lastName.setEnabled(false);
            grades.setEnabled(false);

            button.setText(failureMessage);
            button.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, failureExitMessage, Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}