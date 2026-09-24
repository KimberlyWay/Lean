package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;

// Окно "Масса": вводишь число в любой квадратик - остальные пересчитываются сами
public class MassActivity extends Activity implements TextWatcher {

    EditText editMg, editG, editKg, editT;

    // true, пока мы сами пишем числа в квадратики (чтобы не зациклиться)
    boolean updating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);

        editMg = findViewById(R.id.editMg);
        editG = findViewById(R.id.editG);
        editKg = findViewById(R.id.editKg);
        editT = findViewById(R.id.editT);

        // Следим за изменением текста в каждом квадратике
        editMg.addTextChangedListener(this);
        editG.addTextChangedListener(this);
        editKg.addTextChangedListener(this);
        editT.addTextChangedListener(this);
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

        // Переводим введённое число в граммы
        double grams;
        if (changed == editMg) {
            grams = number / 1000;
        } else if (changed == editG) {
            grams = number;
        } else if (changed == editKg) {
            grams = number * 1000;
        } else {
            grams = number * 1000000;
        }

        // Из граммов - во все остальные квадратики
        updating = true;
        if (changed != editMg) editMg.setText(format(grams * 1000));
        if (changed != editG) editG.setText(format(grams));
        if (changed != editKg) editKg.setText(format(grams / 1000));
        if (changed != editT) editT.setText(format(grams / 1000000));
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
