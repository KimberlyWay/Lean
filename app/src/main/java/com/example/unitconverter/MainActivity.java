package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * Простой конвертер величин: длина, масса и время.
 *
 * Как это работает:
 * 1. Пользователь выбирает величину (длина / масса / время).
 * 2. Вводит число и выбирает, в каких единицах оно указано.
 * 3. Нажимает "Конвертировать" - и мы показываем это же значение
 *    во всех единицах данной величины.
 *
 * Вся "магия" - это перевод введённого числа в базовую единицу
 * (метры, килограммы или секунды), а потом из базовой единицы
 * во все остальные.
 */
public class MainActivity extends AppCompatActivity {

    // Названия величин, которые можно выбрать
    private final String[] categories = {"Длина", "Масса", "Время"};

    // Единицы измерения для каждой величины
    private final String[] lengthUnits = {"мм", "см", "дм", "м", "км"};
    private final String[] massUnits = {"мг", "г", "кг", "т"};
    private final String[] timeUnits = {"с", "мин", "ч", "сутки"};

    // Во сколько раз единица больше базовой (метр / килограмм / секунда)
    private final double[] lengthFactors = {0.001, 0.01, 0.1, 1, 1000};
    private final double[] massFactors = {0.000001, 0.001, 1, 1000};
    private final double[] timeFactors = {1, 60, 3600, 86400};

    private Spinner spinnerCategory;
    private Spinner spinnerUnit;
    private EditText editValue;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        editValue = findViewById(R.id.editValue);
        textResult = findViewById(R.id.textResult);

        Button buttonConvert = findViewById(R.id.buttonConvert);

        // Заполняем список величин (Длина / Масса / Время)
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategory.setAdapter(categoryAdapter);

        // Когда меняется величина - меняем список единиц измерения
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUnitSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ничего не делаем
            }
        });

        // Показываем единицы для первой величины (Длина) сразу при запуске
        updateUnitSpinner(0);

        buttonConvert.setOnClickListener(v -> convert());
    }

    // Заполняет spinnerUnit единицами измерения для выбранной величины
    private void updateUnitSpinner(int categoryPosition) {
        String[] units = getUnitsForCategory(categoryPosition);
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerUnit.setAdapter(unitAdapter);
    }

    private String[] getUnitsForCategory(int categoryPosition) {
        if (categoryPosition == 0) {
            return lengthUnits;
        } else if (categoryPosition == 1) {
            return massUnits;
        } else {
            return timeUnits;
        }
    }

    private double[] getFactorsForCategory(int categoryPosition) {
        if (categoryPosition == 0) {
            return lengthFactors;
        } else if (categoryPosition == 1) {
            return massFactors;
        } else {
            return timeFactors;
        }
    }

    // Главный расчёт: переводим введённое число во все единицы величины
    private void convert() {
        String input = editValue.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_SHORT).show();
            return;
        }

        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Некорректное число", Toast.LENGTH_SHORT).show();
            return;
        }

        int categoryPosition = spinnerCategory.getSelectedItemPosition();
        int unitPosition = spinnerUnit.getSelectedItemPosition();

        String[] units = getUnitsForCategory(categoryPosition);
        double[] factors = getFactorsForCategory(categoryPosition);

        // Переводим введённое значение в базовую единицу (м, кг или с)
        double baseValue = value * factors[unitPosition];

        // Строим результат: baseValue переводим обратно в каждую единицу
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < units.length; i++) {
            double converted = baseValue / factors[i];
            result.append(units[i])
                    .append(": ")
                    .append(String.format(Locale.getDefault(), "%.6g", converted).trim())
                    .append("\n");
        }

        textResult.setText(result.toString().trim());
    }
}
