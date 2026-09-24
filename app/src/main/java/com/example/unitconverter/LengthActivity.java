package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// Окно "Длина": сантиметры и метры
public class LengthActivity extends Activity {

    EditText editNumber;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        editNumber = findViewById(R.id.editNumber);
        textResult = findViewById(R.id.textResult);
    }

    // Кнопка "см → м"
    public void cmToM(View view) {
        double number;
        try {
            number = Double.parseDouble(editNumber.getText().toString());
        } catch (NumberFormatException e) {
            textResult.setText("Введите число");
            return;
        }
        double result = number / 100;
        textResult.setText("Результат: " + result + " м");
    }

    // Кнопка "м → см"
    public void mToCm(View view) {
        double number;
        try {
            number = Double.parseDouble(editNumber.getText().toString());
        } catch (NumberFormatException e) {
            textResult.setText("Введите число");
            return;
        }
        double result = number * 100;
        textResult.setText("Результат: " + result + " см");
    }
}
