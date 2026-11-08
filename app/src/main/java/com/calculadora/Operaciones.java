package com.calculadora;

import java.math.BigDecimal;

final class Operaciones {
    private Operaciones() {}

    static BigDecimal sumar(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    static BigDecimal restar(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    static BigDecimal multiplicar(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    static BigDecimal dividir(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a.divide(b, BigDecimal.ROUND_HALF_UP);
    }
}
