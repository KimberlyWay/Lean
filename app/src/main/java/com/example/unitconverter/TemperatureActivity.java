package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;

// Окно "Температура": вводишь число в любой квадратик - остальные пересчитываются сами
public class TemperatureActivity extends Activity implements TextWatcher {

    EditText editC, editF, editK;

    // true, пока мы сами пишем числа в квадратики (чтобы не зациклиться)
    boolean updating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        editC = findViewById(R.id.editC);
        editF = findViewById(R.id.editF);
        editK = findViewById(R.id.editK);

        // Следим за изменением текста в каждом квадратике
        editC.addTextChangedListener(this);
        editF.addTextChangedListener(this);
        editK.addTextChangedListener(this);
    }

    // Вызывается каждый раз, когда меняется текст в любом квадратике
    @Override
    public void afterTextChanged(Editable text) {
        if (updating) {
            return;
        }

        double number;
        try {
            number = Double.parseDouble(text.toString().replace(',', '.'));
        } catch (NumberFormatException e) {
            return; // там не число - ничего не делаем
        }

        // Квадратик, в который сейчас пишет пользователь
        View changed = getCurrentFocus();

        // Переводим введённое число в градусы Цельсия
        double celsius;
        if (changed == editC) {
            celsius = number;
        } else if (changed == editF) {
            celsius = (number - 32) * 5 / 9;
        } else {
            celsius = number - 273.15;
        }

        // Из Цельсия - во все остальные квадратики
        updating = true;
        if (changed != editC) editC.setText(format(celsius));
        if (changed != editF) editF.setText(format(celsius * 9 / 5 + 32));
        if (changed != editK) editK.setText(format(celsius + 273.15));
        updating = false;
    }

    // Показываем число без лишних нулей: 1.3 вместо 1.3000000001
    String format(double number) {
        return new BigDecimal(number).round(new MathContext(10))
                .stripTrailingZeros().toPlainString();
    }

    // Эти два метода нам не нужны, но Android требует, чтобы они были
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
