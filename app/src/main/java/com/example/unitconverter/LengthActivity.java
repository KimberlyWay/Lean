package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;

// Окно "Длина": вводишь число в любой квадратик - остальные пересчитываются сами
public class LengthActivity extends Activity implements TextWatcher {

    EditText editCm, editDm, editM, editKm;

    // true, пока мы сами пишем числа в квадратики (чтобы не зациклиться)
    boolean updating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        editCm = findViewById(R.id.editCm);
        editDm = findViewById(R.id.editDm);
        editM = findViewById(R.id.editM);
        editKm = findViewById(R.id.editKm);

        // Следим за изменением текста в каждом квадратике
        editCm.addTextChangedListener(this);
        editDm.addTextChangedListener(this);
        editM.addTextChangedListener(this);
        editKm.addTextChangedListener(this);
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

        // Переводим введённое число в метры
        double meters;
        if (changed == editCm) {
            meters = number / 100;
        } else if (changed == editDm) {
            meters = number / 10;
        } else if (changed == editM) {
            meters = number;
        } else {
            meters = number * 1000;
        }

        // Из метров - во все остальные квадратики
        updating = true;
        if (changed != editCm) editCm.setText(format(meters * 100));
        if (changed != editDm) editDm.setText(format(meters * 10));
        if (changed != editM) editM.setText(format(meters));
        if (changed != editKm) editKm.setText(format(meters / 1000));
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
