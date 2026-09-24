package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// Окно "Температура": Цельсий и Фаренгейт
public class TemperatureActivity extends Activity {

    EditText editNumber;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        editNumber = findViewById(R.id.editNumber);
        textResult = findViewById(R.id.textResult);
    }

    // Кнопка "°C → °F"
    public void celsiusToFahrenheit(View view) {
        double number;
        try {
            number = Double.parseDouble(editNumber.getText().toString());
        } catch (NumberFormatException e) {
            textResult.setText("Введите число");
            return;
        }
        double result = number * 9 / 5 + 32;
        textResult.setText("Результат: " + result + " °F");
    }

    // Кнопка "°F → °C"
    public void fahrenheitToCelsius(View view) {
        double number;
        try {
            number = Double.parseDouble(editNumber.getText().toString());
        } catch (NumberFormatException e) {
            textResult.setText("Введите число");
            return;
        }
        double result = (number - 32) * 5 / 9;
        textResult.setText("Результат: " + result + " °C");
    }
}
