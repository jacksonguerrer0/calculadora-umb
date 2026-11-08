package com.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;

public class CalculadoraActivity extends Activity {
    private EditText primero;
    private EditText segundo;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        primero = findViewById(R.id.primero);
        segundo = findViewById(R.id.segundo);
        resultado = findViewById(R.id.resultado);
        findViewById(R.id.sumar).setOnClickListener(v -> calcular(Operaciones::sumar));
        findViewById(R.id.restar).setOnClickListener(v -> calcular(Operaciones::restar));
        findViewById(R.id.multiplicar).setOnClickListener(v -> calcular(Operaciones::multiplicar));
        findViewById(R.id.dividir).setOnClickListener(v -> calcular(Operaciones::dividir));
    }

    private interface Operacion {
        BigDecimal aplicar(BigDecimal a, BigDecimal b);
    }

    private BigDecimal leer(EditText campo) {
        try {
            String valor = campo.getText().toString().trim().replace(',', '.');
            return new BigDecimal(valor);
        } catch (NumberFormatException ex) {
            campo.setError(getString(R.string.invalid));
            campo.requestFocus();
            return null;
        }
    }

    private void calcular(Operacion operacion) {
        BigDecimal a = leer(primero);
        BigDecimal b = a == null ? null : leer(segundo);
        if (a == null || b == null) {
            resultado.setText(R.string.waiting);
            return;
        }
        try {
            BigDecimal valor = operacion.aplicar(a, b);
            resultado.setText(valor.stripTrailingZeros().toPlainString().replace('.', ','));
        } catch (ArithmeticException ex) {
            resultado.setText(R.string.zero);
        } catch (UnsupportedOperationException ex) {
            resultado.setText(R.string.pending);
        }
    }
}
