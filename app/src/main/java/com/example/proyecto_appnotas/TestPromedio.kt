package com.example.proyecto_appnotas

class TestPromedio {
    fun calcularPromedio(nota1: Double?, nota2: Double?): Double {
        val nota1Valida = nota1 ?: 0.0
        val nota2Valida = nota2 ?: 0.0
        return (nota1Valida + nota2Valida) / 2
    }
}