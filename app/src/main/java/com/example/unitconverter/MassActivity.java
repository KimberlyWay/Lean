package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// Окно "Масса": граммы и килограммы
public class MassActivity extends Activity {

    EditText editNumber;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);

        editNumber = findViewById(R.id.editNumber);
        textResult = findViewById(R.id.textResult);
    }

    // Кнопка "г → кг"
    public void gToKg(View view) {
        double number;
        try {
            number = Double.parseDouble(editNumber.getText().toString());
        } catch (NumberFormatException e) {
            textResult.setText("Введите число");
            return;
        }
        double result = number / 1000;
        textResult.setText("Результат: " + result + " кг");
    }

    // Кнопка "кг → г"
    public void kgToG(View view) {
        double number;
        try {
            number = Double.parseDouble(editNumber.getText().toString());
        } catch (NumberFormatException e) {
            textResult.setText("Введите число");
            return;
        }
        double result = number * 1000;
        textResult.setText("Результат: " + result + " г");
    }
}
