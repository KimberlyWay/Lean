package com.example.unitconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Главное окно: три кнопки, каждая открывает своё окно
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Кнопка "Длина"
    public void openLength(View view) {
        Intent intent = new Intent(this, LengthActivity.class);
        startActivity(intent);
    }

    // Кнопка "Масса"
    public void openMass(View view) {
        Intent intent = new Intent(this, MassActivity.class);
        startActivity(intent);
    }

    // Кнопка "Температура"
    public void openTemperature(View view) {
        Intent intent = new Intent(this, TemperatureActivity.class);
        startActivity(intent);
    }
}
