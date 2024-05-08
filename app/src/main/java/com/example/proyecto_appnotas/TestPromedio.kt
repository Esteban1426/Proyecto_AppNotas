package com.example.proyecto_appnotas

class TestPromedio {
    fun calcularPromedio(notas: List<Double>): Double {
        if (notas.size < 2) {
            throw IllegalArgumentException("Se necesitan mínimo 2 notas para calcular el promedio")
        }
        return notas.sum() / notas.size
    }
}